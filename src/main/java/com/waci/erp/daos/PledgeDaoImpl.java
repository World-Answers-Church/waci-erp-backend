package com.waci.erp.daos;

import com.waci.erp.models.finance.FundraisingCause;
import com.waci.erp.models.finance.Pledge;
import com.waci.erp.shared.dao.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link Pledge}
 */
@Repository
public class PledgeDaoImpl extends BaseDAOImpl<Pledge> implements PledgeDao {
}

