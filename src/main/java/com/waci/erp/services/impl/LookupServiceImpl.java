package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.dtos.LookupValueDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.MemberService;
import com.waci.erp.shared.constants.RecordStatus;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.exceptions.ValidationFailedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LookupServiceImpl implements LookupValueService {
    @Autowired
    LookupValueDao lookupValueDao;

    @Override
    public LookupValue save(LookupValueDTO lookupValueDTO) {
        if(StringUtils.isBlank(lookupValueDTO.getValue())){
            throw new OperationFailedException("Missing value");
        }
        if(lookupValueDTO.getType()==null){
            throw new OperationFailedException("Missing type");
        }
        LookupValue existsWithNameAndType=getLookupValueByTypeAndValue(lookupValueDTO.getType(), lookupValueDTO.getValue());
        if(existsWithNameAndType!=null&&existsWithNameAndType.getId()!=lookupValueDTO.getId()){
            throw new OperationFailedException("Lookup value with same name and type exists");
        }
        LookupValue lookupValue= lookupValueDTO.toDBModel();
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



}
