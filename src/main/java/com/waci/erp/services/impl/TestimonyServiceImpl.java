package com.waci.erp.services.impl;

import com.waci.erp.daos.LookupValueDao;
import com.waci.erp.daos.MemberDao;
import com.waci.erp.daos.TestimonyDao;
import com.waci.erp.dtos.BaseCriteria;
import com.waci.erp.dtos.TestimonyDTO;
import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.Testimony;
import com.waci.erp.services.TestimonyService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import com.waci.erp.shared.searchutils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TestimonyServiceImpl implements TestimonyService {
    @Autowired
    TestimonyDao testimonyDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    LookupValueDao lookupValueDao;
    private SearchRequest request;


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

        Member member = memberDao.getReferenceById(instance.getMemberId());
        if (member == null) {
            throw new OperationFailedException("Member with Id not found");
        }

        LookupValue type = lookupValueDao.getLookupValueByTypeAndValue(LookupType.TESTIMONY_TYPE, instance.getTypeName());
        if (type == null) {
            throw new OperationFailedException("Type Not Found");
        }

        Testimony testimony = instance.toDBObject();
        testimony.setMember(member);
        testimony.setType(type);
        return testimonyDao.save(instance.toDBObject());
    }

    @Override
    public List<Testimony> getList(BaseCriteria baseCriteria) {
        SearchRequest request = new SearchRequest().addFilter(new FilterRequest("details", Operator.LIKE, FieldType.CHAR, baseCriteria.getSearchTerm()));
        request.setPage(baseCriteria.getOffset());
        request.setSize(baseCriteria.getLimit());
        SearchSpecification<Testimony> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return testimonyDao.findAll(specification, pageable).toList();
    }

    @Override
    public Testimony getById(long id) {
        return testimonyDao.findById(id).orElseThrow(() -> new OperationFailedException("Not found"));
    }

    @Override
    public List<Testimony> getByMember(Member type) {
        return testimonyDao.findAll();
    }


}
