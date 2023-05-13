package com.waci.erp.daos;

import com.waci.erp.models.finance.Pledge;
import com.waci.erp.models.finance.PledgePayment;
import com.waci.erp.shared.dao.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link PledgePayment}
 */
@Repository
public class PledgePaymentDaoImpl extends BaseDAOImpl<PledgePayment> implements PledgePaymentDao {
}

