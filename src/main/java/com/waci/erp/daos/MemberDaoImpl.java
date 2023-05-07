package com.waci.erp.daos;

import com.waci.erp.models.Member;
import com.waci.erp.shared.dao.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link Member}
 */
@Repository
public class MemberDaoImpl extends BaseDAOImpl<Member> implements MemberDao {
}

