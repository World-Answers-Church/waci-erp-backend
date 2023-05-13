package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.ProphecyDTO;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.models.prayers.Prophecy;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Prophecy}
 */
public interface ProphecyService {
    /**
     * Saves a microservice to the database
     * @param dto
     * @return
     */
    Prophecy save(ProphecyDTO dto);

    int count(Search search);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    List<Prophecy> getList(Search search, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Prophecy getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<Prophecy> getByMember(Member member);

}
