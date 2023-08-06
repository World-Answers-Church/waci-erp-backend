package com.waci.erp.services;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.dtos.OrganisationDTO;
import com.waci.erp.models.prayers.Organisation;

import java.util.List;

/**
 * Handles CRUD operations on the {@link  Organisation}
 */
public interface OrganisationService {
    /**
     * Saves a microservice to the database
     * @param organisationDTO
     * @return
     */
    Organisation save(OrganisationDTO organisationDTO);

    /**
     * Gets a list of microservices following a supplied search term, offset and limit
     * @param search
     * @return
     */
    List<Organisation> getList(Search search,int offset, int limit);

   int count(Search search);

    /**
     * Gets a microservice that matches a given Id
     * @param id
     * @return
     */
    Organisation getOrganisationById(long id);

    /**
     * Gets a microservice that matches a given code
     * @param phoneNumber
     * @return
     */
    Organisation getOrganisationByPhoneNumber(String phoneNumber);

}
