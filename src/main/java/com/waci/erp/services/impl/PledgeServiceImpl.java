package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.PledgeDao;
import com.waci.erp.dtos.PledgeDTO;
import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.services.FundraisingCauseService;
import com.waci.erp.services.MemberService;
import com.waci.erp.services.PledgeService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.utils.CustomSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PledgeServiceImpl implements PledgeService {
    @Autowired
    PledgeDao pledgeDao;

    @Autowired
    MemberService memberService;

    @Autowired
    FundraisingCauseService fundraisingCauseService;


    @Override
    public Pledge save(PledgeDTO dto) {
        Pledge pledge = new Pledge();
        if (dto.getId() > 0) {
            Pledge existsWithId = getById(dto.getId());
            if (existsWithId == null) {
                throw new OperationFailedException("Pledge No Found with Id");
            } else {
                pledge = existsWithId;
            }
        }
        if (dto.getMemberId() == 0) {
            throw new OperationFailedException("Missing member");
        }

        Member member = memberService.getMemberById(dto.getMemberId());
        if (member != null) {
            throw new OperationFailedException("Invalid Pledge type");
        }

        FundraisingCause cause = fundraisingCauseService.getById(dto.getFundraisingCauseId());
        if (cause != null) {
            throw new OperationFailedException("Missing Cause");
        }
        if (dto.getAmount()<=0) {
            throw new OperationFailedException("Invalid amount");
        }

        if (dto.getMemberId() == 0) {
            throw new OperationFailedException("Missing member");
        }

        pledge.setAmount(dto.getAmount());
        pledge.setDatePledged(dto.getDatePledged());
        pledge.setCancellationReason(dto.getCancellationReason());
        pledge.setAmountPaid(0);
        pledge.setDatePledged(dto.getDatePledged());
        pledge.setFundraisingCause(cause);
        pledge.setMember(member);
        if (pledge.isNew()) {
            pledge.setMember(member);
        }
        return pledgeDao.save(pledge);
    }

    @Override
    public List<Pledge> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return pledgeDao.search(search);
    }

    @Override
    public int count(Search search) {
        return pledgeDao.count(search);
    }

    @Override
    public Pledge getById(long id) {
        return pledgeDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<Pledge> getByMember(Member member) {
        Search search = new Search().addFilterEqual("member", member);

        return pledgeDao.searchUnique(search);
    }

    public static Search composeSearchObject(String searchTerm) {
        return CustomSearchUtils.generateSearchTerms(searchTerm, Arrays.asList("details", "member.firstName", "member.lastName"));

    }

}
