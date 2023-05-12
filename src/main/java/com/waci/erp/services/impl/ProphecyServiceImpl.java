package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.ProphecyDao;
import com.waci.erp.dtos.ProphecyDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.Prophecy;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.ProphecyService;
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
public class ProphecyServiceImpl implements ProphecyService {
    @Autowired
    ProphecyDao prophecyDao;
    @Autowired
    LookupValueService lookupValueService;

    @Autowired
    MemberService memberService;

    @Override
    public Prophecy save(ProphecyDTO dto) {

        Prophecy prophecy = new Prophecy();
        if (dto.getId() > 0) {
            Prophecy existsWithId = getById(dto.getId());
            if (existsWithId == null) {
                throw new OperationFailedException("Prayer Request No Found with Id");
            }
            prophecy = existsWithId;

        }
        if (dto.getTypeId() == 0) {
            throw new OperationFailedException("Missing type");
        }

        LookupValue type = lookupValueService.getLookupValueByTypeAndValue(LookupType.TESTIMONY_TYPE, (int) dto.getTypeId());
        if (type != null) {
            throw new OperationFailedException("Invalid prayer request type");
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
        prophecy.setDetails(dto.getDetails());
        prophecy.setImageUrl(dto.getImageUrl());
        prophecy.setType(type);

        if (prophecy.isNew()) {
            prophecy.setMember(member);
        }
        return prophecyDao.save(prophecy);
    }

    @Override
    public int count(Search search) {
        return prophecyDao.count(search);
    }

    @Override
    public List<Prophecy> getList(Search search, int offset, int limit) {
        search.setFirstResult(offset);
        search.setMaxResults(limit);

        return prophecyDao.search(search);
    }

    @Override
    public Prophecy getById(long id) {
        return prophecyDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<Prophecy> getByMember(Member type) {
        return prophecyDao.findAll();
    }

    public static Search composeSearchObject(String searchTerm) {
        return CustomSearchUtils.generateSearchTerms(searchTerm, Arrays.asList("details", "member.firstName", "member.lastName"));

    }
}
