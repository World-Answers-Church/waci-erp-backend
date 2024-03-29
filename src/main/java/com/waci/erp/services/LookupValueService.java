package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.shared.models.Country;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  LookupValue}
 */
public interface LookupValueService {
    /**
     * Saves a microservice to the database
     * @param lookupValueDTO
     * @return
     */
    LookupValue save(LookupValueDTO lookupValueDTO);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param searchTerm
     * @param offset
     * @param limit
     * @return
     */
    List<LookupValue> getList(Search searchTerm, int offset, int limit);

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

    /**
     *
     * @param lookupType
     * @param name
     * @return
     */
   LookupValue getLookupValueByTypeAndValue(LookupType lookupType, String  name);

    LookupValue getLookupValueByTypeAndValue(LookupType lookupType, int  id);

    Country save(Country country);

    List<Country> getCountries(Search search, int offset, int limit);

    long countCountries(Search search);

    long countLookupValues(Search search);
}
