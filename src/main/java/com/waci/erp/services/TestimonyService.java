package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.prayers.Member;
import com.waci.erp.models.prayers.Testimony;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Testimony}
 */
public interface TestimonyService {
    /**
     * Saves a microservice to the database
     * @param testimony
     * @return
     */
    Testimony save(TestimonyDTO testimony);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @return
     */
    List<Testimony> getList(Search search, int offset, int limit);

    int count(Search search);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Testimony getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<Testimony> getByMember(Member member);

}
