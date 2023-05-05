package com.waci.erp.daos;

import com.waci.erp.shared.models.Country;

public interface CountryDao extends BaseDao<Country> {
Country getByName(String name);
}

