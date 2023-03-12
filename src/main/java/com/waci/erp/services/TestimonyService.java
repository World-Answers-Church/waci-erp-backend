package com.waci.erp.services;

import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.TestimonyDTO;
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
    Testimony save(TestimonyDTO testimony);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @return
     */
    List<Testimony> getList(BaseCriteria request);

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
