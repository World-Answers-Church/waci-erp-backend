package com.waci.erp.daos;

import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Testimony;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object class for {@link Member}
 */
public interface TestimonyDao extends JpaRepository<Testimony, Long> {
}

