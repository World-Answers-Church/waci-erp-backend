package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PledgePaymentDTO;
import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgePayment;
import com.waci.erp.models.prayers.Member;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  PledgePayment}
 */
public interface PledgePaymentService {
    /**
     * Saves a microservice to the database
     * @param dto
     * @return
     */
    PledgePayment save(PledgePaymentDTO dto);

    int count(Search search);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    List<PledgePayment> getList(Search search, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    PledgePayment getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<PledgePayment> getByMember(Member member);

    /**
     *
     * @param pledge
     * @return
     */
     List<PledgePayment> getByPledge(Pledge pledge) ;

   double getTotalPayments(Pledge pledge) ;
}
