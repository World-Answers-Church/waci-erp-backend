package com.waci.erp.services;

import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Prophecy;
import com.waci.erp.models.Testimony;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Prophecy}
 */
public interface ProphecyService {
    /**
     * Saves a microservice to the database
     * @param testimony
     * @return
     */
    Prophecy save(Prophecy testimony);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param searchTerm
     * @param offset
     * @param limit
     * @return
     */
    List<Prophecy> getList(String searchTerm, int offset, int limit);

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
