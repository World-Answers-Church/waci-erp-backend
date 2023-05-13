package com.waci.erp.daos;

import com.waci.erp.models.prayers.Member;
import com.waci.erp.models.prayers.PrayerRequest;
import com.waci.erp.shared.dao.BaseDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link Member}
 */
@Repository
public class PrayerRequestDaoImpl extends BaseDAOImpl<PrayerRequest> implements PrayerRequestDao {
}

