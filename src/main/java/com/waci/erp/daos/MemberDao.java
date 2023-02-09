package com.waci.erp.daos;

import com.waci.erp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access Object class for {@link Member}
 */
public interface MemberDao extends JpaRepository<Member, Long> {
Member getMemberByPhoneNumber(String phoneNumber);
}

