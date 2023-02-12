package com.waci.erp.daos;

import com.waci.erp.models.LookupValue;
import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object class for {@link Member}
 */
public interface LookupValueDao extends JpaRepository<LookupValue, Long> {
}

