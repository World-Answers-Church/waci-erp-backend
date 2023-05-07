package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.models.Member;
import com.waci.erp.services.MemberService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.utils.CustomSearchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
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

        Member existsWithCode= getMemberByPhoneNumber(member.getPhoneNumber());
        if(existsWithCode!=null&&existsWithCode.getId()!= member.getId()){
            throw new OperationFailedException("Member with same phone number exists");
        }

        return memberDao.save(member);
    }

    @Override
    public List<Member> getMembers(Search search,int offset, int limit ) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return memberDao.search(search);
    }

    @Override
    public Member getMemberById(long id) {
        return memberDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public Member getMemberByPhoneNumber(String phoneNumber) {
        return memberDao.searchUnique(new Search().addFilterEqual("phoneNumber",phoneNumber));
    }

    public static Search composeSearchObject(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList(
                        "firstName",
                        "lastName",
                        "middleName",
                        "physicalAddress",
                        "phoneNumber",
                        "emailAddress",
                        "nin",
                        "occupation"));

        return  search;
    }
}
