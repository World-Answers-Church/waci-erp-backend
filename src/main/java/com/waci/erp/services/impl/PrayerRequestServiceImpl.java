package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.PrayerRequestDao;
import com.waci.erp.dtos.PrayerRequestDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.PrayerRequestService;
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
public class PrayerRequestServiceImpl implements PrayerRequestService {
    @Autowired
    PrayerRequestDao prayerRequestDao;
    @Autowired
    LookupValueService lookupValueService;

    @Autowired
    MemberService memberService;

    @Override
    public PrayerRequest save(PrayerRequestDTO dto) {
        PrayerRequest prayerRequest = new PrayerRequest();
        if (dto.getId() > 0) {
            prayerRequest = getById(dto.getId());
            if (prayerRequest == null) {
                throw new OperationFailedException("Prayer Request No Found with Id");
            }
        }
        if (dto.getTypeId() == 0) {
            throw new OperationFailedException("Missing type");
        }

        LookupValue type = lookupValueService.getLookupValueByTypeAndValue(LookupType.PRAYER_REQUEST_TYPE, (int) dto.getTypeId());
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
        prayerRequest.setDetails(dto.getDetails());
        prayerRequest.setImageUrl(dto.getImageUrl());
        prayerRequest.setType(type);
        prayerRequest.setMember(member);
        return prayerRequestDao.save(prayerRequest);
    }

    @Override
    public List<PrayerRequest> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return prayerRequestDao.search(search);
    }

    @Override
    public PrayerRequest getById(long id) {
        return prayerRequestDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<PrayerRequest> getByMember(Member type) {
        return prayerRequestDao.findAll();
    }
    @Override
    public int count(Search search) {
        return prayerRequestDao.count(search);
    }
    public static Search composeSearchObject(String searchTerm) {
       return CustomSearchUtils.generateSearchTerms(searchTerm,   Arrays.asList("details","member.firstName","member.lastName"));
    }
}
