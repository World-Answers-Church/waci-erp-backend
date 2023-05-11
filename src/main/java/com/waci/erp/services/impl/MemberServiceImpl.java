package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.dtos.MemberDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.services.LookupValueService;
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

@Autowired
LookupValueService lookupValueDao;
    @Override
    public Member saveMember(MemberDTO dto) {
        Member member= new Member();
        if(dto.getId()>0){
            member=getMemberById(dto.getId());
            if(member==null){
                throw new OperationFailedException("Member No Found with Id");
            }
        }
        if(dto.getYearJoined()==0){
            throw new OperationFailedException("Missing joined year");
        }
        if(StringUtils.isBlank(dto.getFirstName())){
            throw new OperationFailedException("Missing first name");
        }

        if(StringUtils.isBlank(dto.getLastName())){
            throw new OperationFailedException("Missing last name");
        }

        Member existsWithCode= getMemberByPhoneNumber(dto.getPhoneNumber());
        if(existsWithCode!=null&&existsWithCode.getId()!= dto.getId()){
            throw new OperationFailedException("Member with same phone number exists");
        }

        LookupValue salutation= lookupValueDao.getLookupValueByTypeAndValue(LookupType.TESTIMONY_TYPE,dto.getSalutationId());
        if(salutation==null){
            throw new OperationFailedException("Invalid salutation value");
        }

        LookupValue occupation= lookupValueDao.getLookupValueByTypeAndValue(LookupType.OCCUPATION,dto.getOccupationId());
        if(occupation==null){
            throw new OperationFailedException("Invalid occupation value");
        }


        member.setId(dto.getId());
        member.setEmailAddress(dto.getEmailAddress());
        member.setFirstName(dto.getFirstName());
        member.setImageUrl(dto.getImageUrl());
        member.setLastName(dto.getLastName());
        member.setOccupation(occupation);
        member.setNin(dto.getNin());
        member.setPhoneNumber(dto.getPhoneNumber());
        member.setPhysicalAddress(dto.getPhysicalAddress());
        member.setSalutation(salutation);
        member.setMiddleName(dto.getMiddleName());
        member.setYearJoined(dto.getYearJoined());

        return memberDao.save(member);
    }

    @Override
    public List<Member> getMembers(Search search,int offset, int limit ) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return memberDao.search(search);
    }

    @Override
    public int countMembers(Search search ) {
        return memberDao.count(search);
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
