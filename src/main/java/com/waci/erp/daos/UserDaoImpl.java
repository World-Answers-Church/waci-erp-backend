package com.waci.erp.daos;

import com.waci.erp.shared.dao.BaseDAOImpl;
import com.waci.erp.shared.models.User;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object class for {@link User}
 */
@Repository
public class UserDaoImpl extends BaseDAOImpl<User> implements UserDao{

}

