package com.waci.erp.services;

import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;

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
    Testimony save(Testimony testimony);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param searchTerm
     * @param offset
     * @param limit
     * @return
     */
    List<Testimony> getList(String searchTerm, int offset, int limit);

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
