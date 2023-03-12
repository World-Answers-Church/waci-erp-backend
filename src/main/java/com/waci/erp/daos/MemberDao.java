package com.waci.erp.daos;

import com.waci.erp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Data Access Object class for {@link Member}
 */
public interface MemberDao extends JpaRepository<Member, Long> , JpaSpecificationExecutor<Member> {
Member getMemberByPhoneNumber(String phoneNumber);
}

