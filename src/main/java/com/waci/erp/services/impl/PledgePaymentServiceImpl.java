package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.daos.PledgeDao;
import com.waci.erp.daos.PledgePaymentDao;
import com.waci.erp.dtos.PledgePaymentDTO;
import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgePayment;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.services.PledgePaymentService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.exceptions.ValidationFailedException;
import com.waci.erp.shared.utils.CustomSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PledgePaymentServiceImpl implements PledgePaymentService {
    @Autowired
    PledgePaymentDao pledgePaymentDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    PledgeDao pledgeDao;


    @Override
    public PledgePayment save(PledgePaymentDTO dto) {
        PledgePayment pledgePayment = new PledgePayment();
        if (dto.getId() > 0) {
            pledgePayment = getById(dto.getId());
            if (pledgePayment == null) {
                throw new OperationFailedException("Pledge Payment No Found with Id");
            }
        }
        if (dto.getPledgeId() == 0) {
            throw new OperationFailedException("Missing PledgeId");
        }


        if (dto.getDatePaid() == null) {
            throw new OperationFailedException("Missing date");
        }

        if (dto.getAmount() <= 0) {
            throw new OperationFailedException("Invalid payment amount");
        }

        Pledge pledge = pledgeDao.findById(dto.getPledgeId()).orElseThrow(() -> new ValidationFailedException("Pledge not found"));

        pledgePayment.setId(dto.getId());
        pledgePayment.setPledge(pledge);
        pledgePayment.setAmount(dto.getAmount());
        pledgePayment.setDatePaid(dto.getDatePaid());

        return pledgePaymentDao.save(pledgePayment);
    }

    @Override
    public List<PledgePayment> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);

        return pledgePaymentDao.search(search);
    }

    @Override
    public int count(Search search) {
        return pledgePaymentDao.count(search);
    }

    @Override
    public PledgePayment getById(long id) {
        return pledgePaymentDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<PledgePayment> getByMember(Member member) {
        return pledgePaymentDao.search(new Search().addFilterEqual("pledge.member", member));
    }


    public List<PledgePayment> getByPledge(Pledge pledge) {
        return pledgePaymentDao.search(new Search().addFilterEqual("pledge", pledge));
    }
    public double getTotalPayments(Pledge pledge) {
        return pledgePaymentDao.searchUnique(new Search().addFilterEqual("pledge", pledge)
                .addField("amount", Field.OP_SUM));
    }

    public static Search composeSearchObject(String searchTerm) {

        return CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList(
                        "pledge.member.firstName",
                        "pledge.member.lastName",
                        "pledge.fundraisingCause.name",
                        "pledge.member.phoneNumber"));
    }
}
