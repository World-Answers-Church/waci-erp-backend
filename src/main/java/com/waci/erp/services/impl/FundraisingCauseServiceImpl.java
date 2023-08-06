package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.FundraisingCauseDao;
import com.waci.erp.dtos.FundraisingCauseDTO;
import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.models.finance.FundraisingPlanTypes;
import com.waci.erp.models.finance.ReccuringPaymentFrequency;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.services.FundraisingCauseService;
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
public class FundraisingCauseServiceImpl implements FundraisingCauseService {
    @Autowired
    FundraisingCauseDao fundraisingCauseDao;
    @Autowired
    LookupValueService lookupValueService;

    @Autowired
    MemberService memberService;

    @Override
    public FundraisingCause save(FundraisingCauseDTO dto) {

        FundraisingCause fundraisingCause = new FundraisingCause();
        if (dto.getId() > 0) {
            FundraisingCause existsWithId = getById(dto.getId());
            if (existsWithId == null) {
                throw new OperationFailedException("Cause Not Found with Id");
            }
            fundraisingCause = existsWithId;

        }


        LookupValue type = lookupValueService.getLookupValueByTypeAndValue(LookupType.FUNDRAISING_CAUSE_CATEGORIES, (int) dto.getCategoryId());


        FundraisingPlanTypes fundraisingPlanType = FundraisingPlanTypes.getById(dto.getFundraisingPlanTypeId());
        if (fundraisingPlanType == null) {
            throw new OperationFailedException("Invalid Fundraising Plan Type");
        }

        if (StringUtils.isBlank(dto.getName())) {
            throw new OperationFailedException("Missing name");
        }
     fundraisingCause.setCategory(type);
        fundraisingCause.setFundraisingPlanType(fundraisingPlanType);
        fundraisingCause.setMinimumContribution(dto.getMinimumContribution());
        fundraisingCause.setTargetAmount(dto.getTargetAmount());
        fundraisingCause.setPeriodicContributionAmount(dto.getPeriodicContributionAmount());
       // ReccuringPaymentFrequency reccuringPaymentFrequency = ReccuringPaymentFrequency.getById(dto.getFundraisingPlanTypeId());
      //  fundraisingCause.setReccuringPaymentFrequency(dto.getReccuringPaymentFrequencyId());
        fundraisingCause.setDescription(dto.getDescription());
        fundraisingCause.setImageUrl(dto.getImageUrl());
        fundraisingCause.setName(dto.getName());

        return fundraisingCauseDao.save(fundraisingCause);
    }

    @Override
    public int count(Search search) {
        return fundraisingCauseDao.count(search);
    }

    @Override
    public List<FundraisingCause> getList(Search search, int offset, int limit) {
        search.setFirstResult(offset);
        search.setMaxResults(limit);

        return fundraisingCauseDao.search(search);
    }

    @Override
    public FundraisingCause getById(long id) {
        return fundraisingCauseDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<FundraisingCause> getByCategory(LookupValue type) {
        return fundraisingCauseDao.findAll();
    }

    public static Search composeSearchObject(String searchTerm) {
        return CustomSearchUtils.generateSearchTerms(searchTerm, Arrays.asList("details", "member.firstName", "member.lastName"));

    }
}
