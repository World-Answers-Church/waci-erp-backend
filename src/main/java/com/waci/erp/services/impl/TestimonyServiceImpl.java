package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.daos.TestimonyDao;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.LookupValueService;
import com.waci.erp.services.TestimonyService;
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
public class TestimonyServiceImpl implements TestimonyService {
    @Autowired
    TestimonyDao testimonyDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    LookupValueService lookupValueService;


    @Override
    public Testimony save(TestimonyDTO instance) {

        if (instance.getType() == null) {
            throw new OperationFailedException("Missing type");
        }

        if (instance.getMemberId() == 0) {
            throw new OperationFailedException("Missing member");
        }

        if (StringUtils.isBlank(instance.getDetails())) {
            throw new OperationFailedException("Missing details");
        }

        if (StringUtils.isBlank(instance.getTypeName())) {
            throw new OperationFailedException("Missing type name");
        }

        Member member = memberDao.getReference(instance.getMemberId());
        if (member == null) {
            throw new OperationFailedException("Member with Id not found");
        }

        LookupValue type = lookupValueService.getLookupValueByTypeAndValue(LookupType.TESTIMONY_TYPE, instance.getTypeName());
        if (type == null) {
            throw new OperationFailedException("Type Not Found");
        }

        Testimony testimony = instance.toDBObject();
        testimony.setMember(member);
        testimony.setType(type);
        return testimonyDao.save(instance.toDBObject());
    }

    @Override
    public List<Testimony> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return testimonyDao.search(search);
    }

    @Override
    public Testimony getById(long id) {
        return testimonyDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<Testimony> getByMember(Member member) {
        Search search= new Search().addFilterEqual("member",member);

        return testimonyDao.searchUnique(search);
    }
    public static Search composeSearchObject(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,   Arrays.asList("name","description"));

        return  search;
    }

}
