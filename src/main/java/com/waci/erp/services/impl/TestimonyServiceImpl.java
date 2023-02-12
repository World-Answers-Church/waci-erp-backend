package com.waci.erp.services.impl;

import com.waci.erp.daos.ProphecyDao;
import com.waci.erp.daos.TestimonyDao;
import com.waci.erp.models.Member;
import com.waci.erp.models.Prophecy;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.ProphecyService;
import com.waci.erp.services.TestimonyService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TestimonyServiceImpl implements TestimonyService {
    @Autowired
    TestimonyDao memberDao;


    @Override
    public Testimony save(Testimony member) {

        if(member.getType()==null){
            throw new OperationFailedException("Missing type");
        }

        if(StringUtils.isBlank(member.getDetails())){
            throw new OperationFailedException("Missing details");
        }

        return memberDao.save(member);
    }

    @Override
    public List<Testimony> getList(String searchTerm, int offset, int limit) {
     // return memberDao.findAll(new CustomPageable(offset,limit)).toList();
        return memberDao.findAll();
    }

    @Override
    public Testimony getById(long id) {
        return memberDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public List<Testimony> getByMember(Member type) {
        return memberDao.findAll();
    }


}
