package com.waci.erp.services.impl;

import com.waci.erp.daos.MemberDao;
import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.models.Member;
import com.waci.erp.services.MemberService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.utils.CustomPageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao memberDao;


    @Override
    public Member saveMember(Member member) {
        if(member.getYearJoined()==0){
            throw new OperationFailedException("Missing joined year");
        }
        if(StringUtils.isBlank(member.getFirstName())){
            throw new OperationFailedException("Missing first name");
        }

        if(StringUtils.isBlank(member.getLastName())){
            throw new OperationFailedException("Missing last name");
        }

        Member existsWithCode= memberDao.getMemberByPhoneNumber(member.getPhoneNumber());
        if(existsWithCode!=null&&existsWithCode.getId()!= member.getId()){
            throw new OperationFailedException("Member with same phone number exists");
        }

        return memberDao.save(member);
    }

    @Override
    public List<Member> getMembers(BaseCriteria baseCriteria) {
     // return memberDao.findAll(new CustomPageable(offset,limit)).toList();
        return memberDao.findAll();
    }

    @Override
    public Member getMemberById(long id) {
        return memberDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public Member getMemberByPhoneNumber(String phoneNumber) {
        return memberDao.getMemberByPhoneNumber(phoneNumber);
    }

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
            .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("id");
}
