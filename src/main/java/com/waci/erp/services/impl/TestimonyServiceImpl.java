package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.TestimonyDao;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.TestimonyService;
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
public class TestimonyServiceImpl implements TestimonyService {
    @Autowired
    TestimonyDao testimonyDao;

    @Autowired
    MemberService memberService;

    @Autowired
    LookupValueService lookupValueService;


    @Override
    public Testimony save(TestimonyDTO dto) {
        Testimony testimony = new Testimony();
        if (dto.getId() > 0) {
            Testimony existsWithId = getById(dto.getId());
            if (existsWithId == null) {
                throw new OperationFailedException("Testimony No Found with Id");
            } else {
                testimony = existsWithId;
            }
        }
        if (dto.getTypeId() == 0) {
            throw new OperationFailedException("Missing type");
        }

        LookupValue type = lookupValueService.getLookupValueByTypeAndValue(LookupType.TESTIMONY_TYPE, (int) dto.getTypeId());
        if (type != null) {
            throw new OperationFailedException("Invalid Testimony type");
        }
        if (StringUtils.isBlank(dto.getDetails())) {
            throw new OperationFailedException("Missing details");
        }

        if (dto.getMemberId() == 0) {
            throw new OperationFailedException("Missing member");
        }
        Member member = memberService.getMemberById(dto.getMemberId());
        if (member == null) {
            throw new OperationFailedException("Missing member");
        }
        testimony.setDetails(dto.getDetails());
        testimony.setImageUrl(dto.getImageUrl());
        testimony.setType(type);
        if (testimony.isNew()) {
            testimony.setMember(member);
        }
        return testimonyDao.save(testimony);
    }

    @Override
    public List<Testimony> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return testimonyDao.search(search);
    }

    @Override
    public int count(Search search) {
        return testimonyDao.count(search);
    }

    @Override
    public Testimony getById(long id) {
        return testimonyDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<Testimony> getByMember(Member member) {
        Search search = new Search().addFilterEqual("member", member);

        return testimonyDao.searchUnique(search);
    }

    public static Search composeSearchObject(String searchTerm) {
        return CustomSearchUtils.generateSearchTerms(searchTerm, Arrays.asList("details", "member.firstName", "member.lastName"));

    }

}
