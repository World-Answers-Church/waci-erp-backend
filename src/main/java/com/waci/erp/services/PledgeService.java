package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PledgeDTO;
import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.prayers.Member;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Pledge}
 */
public interface PledgeService {
    /**
     * Saves a microservice to the database
     * @param dto
     * @return
     */
    Pledge save(PledgeDTO dto);

    int count(Search search);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    List<Pledge> getList(Search search, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Pledge getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<Pledge> getByMember(Member member);

}
