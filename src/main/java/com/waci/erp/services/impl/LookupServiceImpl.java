package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.CountryDao;
import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.shared.constants.RecordStatus;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.models.Country;
import com.waci.erp.shared.utils.CustomSearchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class LookupServiceImpl implements LookupValueService {
    @Autowired
    LookupValueDao lookupValueDao;
    @Autowired
    CountryDao countryDao;
    @Override
    public LookupValue save(LookupValueDTO dto) {
        if(StringUtils.isBlank(dto.getValue())){
            throw new OperationFailedException("Missing value");
        }
        if(dto.getTypeId()==0){
            throw new OperationFailedException("Missing type");
        }

        LookupType lookupType= LookupType.getById(dto.getTypeId());
        if(lookupType==null){
            throw new OperationFailedException("Invalid type");
        }

        LookupValue existsWithNameAndType=getLookupValueByTypeAndValue(lookupType, dto.getValue());
        if(existsWithNameAndType!=null&&existsWithNameAndType.getId()!=dto.getId()){
            throw new OperationFailedException("Lookup value with same name and type exists");
        }
        LookupValue lookupValue= new LookupValue();
        if(dto.getId()>0){
            LookupValue existsWithId=getById(dto.getId());
            if(existsWithId==null){
                throw new OperationFailedException("Lookup value with Id not found");
            }
            lookupValue=existsWithId;
        }

        lookupValue.setType(lookupType);
        lookupValue.setDescription(dto.getDescription());
        lookupValue.setValue(dto.getValue());
        lookupValue.setImageUrl(dto.getImageUrl());
        return lookupValueDao.save(lookupValue);
    }

    public LookupValue getLookupValueByTypeAndValue(LookupType lookupType, String  name){
        return lookupValueDao.searchUnique(new Search().addFilterEqual("type",lookupType)
                .addFilterEqual("value",name));

    }
    public LookupValue getLookupValueByTypeAndValue(LookupType lookupType, int  id){
        return lookupValueDao.searchUnique(new Search().addFilterEqual("type",lookupType)
                .addFilterEqual("id",id));

    }

    @Override
    public List<LookupValue> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return lookupValueDao.search(search);
    }

    @Override
    public LookupValue getById(long id) {
        return lookupValueDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public List<LookupValue> getByType(LookupType type) {
        Search search= new Search()
                .addFilterEqual("recordStatus", RecordStatus.ACTIVE)
                .addFilterEqual("type",type);
        return lookupValueDao.search(search);
    }

    @Override
    public Country save(Country country) {
        if(StringUtils.isBlank(country.getName())){
            throw new OperationFailedException("Missing name");
        }

        if(StringUtils.isBlank(country.getPostalCode())){
            throw new OperationFailedException("Missing code");
        }

        Country existsWithName=getByName(country.getName());
        if(existsWithName!=null&&existsWithName.getId()!=country.getId()){
            throw new OperationFailedException("Country value with same name and type exists");
        }

        return countryDao.save(country);
    }
    Country getByName(String name){
        return lookupValueDao.searchUnique(
                new Search().addFilterEqual("name",name) );
    };
    @Override
    public List<Country> getCountries(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return countryDao.search(search);
    }


    @Override
    public long countCountries(Search search) {
        return countryDao.count(search);

    }

    @Override
    public long countLookupValues(Search search) {
        return lookupValueDao.count(search);

    }

    public static Search composeSearchObjectForCountry(String searchTerm) {
        com.googlecode.genericdao.search.Search search = CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList("name",
                        "postalCode",
                        "currencyCode"));
        search.addSortDesc("id");


        return  search;
    }

    public static Search composeSearchObjectForLookupValue(String searchTerm) {
        com.googlecode.genericdao.search.Search search = CustomSearchUtils.generateSearchTerms(searchTerm,
                Arrays.asList("description",
                        "value"));
        search.addSortDesc("id");


        return  search;
    }
}
