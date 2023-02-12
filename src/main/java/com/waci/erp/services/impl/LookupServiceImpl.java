package com.waci.erp.services.impl;

import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.MemberService;
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
    LookupValueDao memberDao;


    @Override
    public LookupValue save(LookupValue member) {

        if(StringUtils.isBlank(member.getValue())){
            throw new OperationFailedException("Missing value");
        }

        if(member.getType()==null){
            throw new OperationFailedException("Missing type");
        }

        return memberDao.save(member);
    }

    @Override
    public List<LookupValue> getList(String searchTerm, int offset, int limit) {
     // return memberDao.findAll(new CustomPageable(offset,limit)).toList();
        return memberDao.findAll();
    }

    @Override
    public LookupValue getById(long id) {
        return memberDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public List<LookupValue> getByType(LookupType type) {
        return memberDao.findAll();
    }


}
