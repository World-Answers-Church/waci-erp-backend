package com.waci.erp.daos;

import com.waci.erp.models.LookupType;
import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Data Access Object class for {@link LookupValue}
 */
public interface LookupValueDao extends JpaRepository<LookupValue, Long> {
    LookupValue getLookupValueByTypeAndValue(LookupType lookupType,String value);

    List<LookupValue> getLookupValuesByType(LookupType lookupType);
}

