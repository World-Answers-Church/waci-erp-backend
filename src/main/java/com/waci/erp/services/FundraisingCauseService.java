package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.FundraisingCauseDTO;
import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.models.prayers.LookupValue;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  FundraisingCause}
 */
public interface FundraisingCauseService {
    /**
     * Saves a microservice to the database
     * @param fundraisingCause
     * @return
     */
    FundraisingCause save(FundraisingCauseDTO fundraisingCause);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @return
     */
    List<FundraisingCause> getList(Search search, int offset, int limit);

    int count(Search search);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    FundraisingCause getById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param lookupValue
     * @return
     */
    List<FundraisingCause> getByCategory(LookupValue lookupValue);

}
