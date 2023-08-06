package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.OrganisationDao;
import com.waci.erp.dtos.OrganisationDTO;
import com.waci.erp.models.prayers.LookupType;
import com.waci.erp.models.prayers.LookupValue;
import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.OrganisationService;
import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.utils.CustomSearchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrganisationServiceImpl implements OrganisationService {
    @Autowired
    OrganisationDao organisationDao;

@Autowired
LookupValueService lookupValueDao;
    @Override
    public Organisation save(OrganisationDTO dto) {
        Organisation organisation= new Organisation();
        if(dto.getId()>0){
            organisation=getOrganisationById(dto.getId());
            if(organisation==null){
                throw new OperationFailedException("Organisation No Found with Id");
            }
        }
        if(dto.getCategoryId()==0){
            throw new OperationFailedException("Missing category");
        }
        if(StringUtils.isBlank(dto.getName())){
            throw new OperationFailedException("Missing name");
        }


        Organisation existsWithCode= getOrganisationByPhoneNumber(dto.getPrimaryPhoneNumber());
        if(existsWithCode!=null&&existsWithCode.getId()!= dto.getId()){
            throw new OperationFailedException("Organisation with same primary phone number exists");
        }

        LookupValue category= lookupValueDao.getLookupValueByTypeAndValue(LookupType.ORGANISATION_CATEGORIES, (int) dto.getCategoryId());

        organisation.setId(dto.getId());
        organisation.setName(dto.getName());
        organisation.setCategory(category);
        organisation.setEmailAddress(dto.getEmailAddress());
        organisation.setPhysicalAddress(dto.getPhysicalAddress());
        organisation.setPrimaryPhoneNumber(dto.getPrimaryPhoneNumber());
        organisation.setOtherPhoneNumber(dto.getOtherPhoneNumber());
        organisation.setLogoUrl(dto.getLogoUrl());
        organisation.setWebsite(dto.getWebsite());

        return organisationDao.save(organisation);
    }

    @Override
    public List<Organisation> getList(Search search,int offset, int limit ) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return organisationDao.search(search);
    }


    @Override
    public int count(Search search ) {
        return organisationDao.count(search);
    }

    @Override
    public Organisation getOrganisationById(long id) {
        return organisationDao.findById(id).orElseThrow(()->new OperationFailedException("Organisation Not found"));
    }

    @Override
    public Organisation getOrganisationByPhoneNumber(String phoneNumber) {
        return organisationDao.searchUnique(new Search().addFilterEqual("phoneNumber",phoneNumber));
    }

    public static Search composeSearchObject(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList(
                        "name",
                        "emailAddress",
                        "physicalAddress",
                        "primaryPhoneNumber",
                        "otherPhoneNumber"));

        return  search;
    }
}
