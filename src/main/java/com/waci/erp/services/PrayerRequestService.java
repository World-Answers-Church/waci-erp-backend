package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.PrayerRequestDTO;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Testimony;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Testimony}
 */
public interface PrayerRequestService {
    /**
     * Saves a microservice to the database
     * @param prayerRequestDTO
     * @return
     */
    PrayerRequest save(PrayerRequestDTO prayerRequestDTO);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @param offset
     * @param limit
     * @return
     */
    List<PrayerRequest> getList(Search search, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    PrayerRequest getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<PrayerRequest> getByMember(Member member);

    int count(Search search);
}
