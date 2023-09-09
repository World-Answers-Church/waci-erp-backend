package com.waci.erp.daos;

import com.waci.erp.models.prayers.Organisation;
import com.waci.erp.models.prayers.OrganisationSetting;
import com.waci.erp.shared.dao.BaseDAOImpl;
import com.waci.erp.shared.models.Country;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link Country}
 */
@Repository
public class OrganisationSettingDaoImpl extends BaseDAOImpl<OrganisationSetting> implements OrganisationSettingsDao {
}

