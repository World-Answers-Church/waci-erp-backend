package com.waci.erp.services.impl;

import com.googlecode.genericdao.search.Search;
import com.waci.erp.daos.PrayerRequestDao;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.services.PrayerRequestService;
import com.waci.erp.shared.exceptions.OperationFailedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrayerRequestServiceImpl implements PrayerRequestService {
    @Autowired
    PrayerRequestDao memberDao;


    @Override
    public PrayerRequest save(PrayerRequest member) {

        if(member.getType()==null){
            throw new OperationFailedException("Missing type");
        }

        if(StringUtils.isBlank(member.getDetails())){
            throw new OperationFailedException("Missing details");
        }

        return memberDao.save(member);
    }

    @Override
    public List<PrayerRequest> getList(Search search, int offset, int limit) {
        search.setMaxResults(limit);
        search.setFirstResult(offset);
        return memberDao.search(search);
    }

    @Override
    public PrayerRequest getById(long id) {
        return memberDao.findById(id).orElseThrow(()->new OperationFailedException("Not found"));
    }

    @Override
    public List<PrayerRequest> getByMember(Member type) {
        return memberDao.findAll();
    }


}
