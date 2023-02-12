package com.waci.erp.daos;

import com.waci.erp.models.Member;
import com.waci.erp.models.PrayerRequest;
import com.waci.erp.models.Prophecy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object class for {@link Member}
 */
public interface ProphecyDao extends JpaRepository<Prophecy, Long> {
}

