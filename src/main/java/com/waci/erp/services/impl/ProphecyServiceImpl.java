package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.PrayerRequestDao;
import com.waci.erp.daos.ProphecyDao;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Prophecy;
import com.waci.erp.services.PrayerRequestService;
import com.waci.erp.services.ProphecyService;
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
public class ProphecyServiceImpl implements ProphecyService {
    @Autowired
    ProphecyDao prophecyDao;


    @Override
    public Prophecy save(Prophecy member) {

        if(member.getType()==null){
            throw new OperationFailedException("Missing type");
        }

        if(StringUtils.isBlank(member.getDetails())){
            throw new OperationFailedException("Missing details");
        }

        return prophecyDao.save(member);
    }

    @Override
    public List<Prophecy> getList(Search search, int offset, int limit) {
        search.setFirstResult(offset);
        search.setMaxResults(limit);

        return prophecyDao.search(search);
    }

    @Override
    public Prophecy getById(long id) {
        return prophecyDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public List<Prophecy> getByMember(Member type) {
        return prophecyDao.findAll();
    }

    public static Search composeSearchObjectForProphecy(String searchTerm) {
        Search search = CustomSearchUtils.generateSearchTerms(searchTerm,   Arrays.asList("name","description"));

        return  search;
    }
}
