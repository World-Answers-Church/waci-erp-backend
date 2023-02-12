package com.waci.erp.services;

import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  LookupValue}
 */
public interface LookupValueService {
    /**
     * Saves a microservice to the database
     * @param testimony
     * @return
     */
    LookupValue save(LookupValue testimony);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param searchTerm
     * @param offset
     * @param limit
     * @return
     */
    List<LookupValue> getList(String searchTerm, int offset, int limit);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    LookupValue getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param member
     * @return
     */
    List<LookupValue> getByType(LookupType member);

}
