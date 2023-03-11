package com.waci.erp.daos;

import com.waci.erp.models.Testimony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Data Access Object class for {@link Testimony}
 */
public interface TestimonyDao extends JpaRepository<Testimony, Long>,
        JpaSpecificationExecutor<Testimony> {
}

