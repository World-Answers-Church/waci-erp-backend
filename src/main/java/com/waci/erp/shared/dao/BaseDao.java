package com.waci.erp.shared.dao;

import java.math.BigInteger;
import java.util.Optional;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.waci.erp.shared.models.BaseEntity;
import com.waci.erp.shared.models.User;

public interface BaseDao<T extends BaseEntity> extends GenericDAO<T, Long> {

    /**
     * This method is used to save changes to the database and wait for the transaction to complete.
     *
     * @param entity
     * @param loggedInUser
     * @return
     */
    T save(T entity, User loggedInUser);

    /**
     * This method is used to save changes and immediately flush the changes to the database
     *
     * @param entity
     * @param loggedInUser
     * @return
     */
    T saveAndFlush(T entity, User loggedInUser);

    /**
     *
     * @param id
     * @return
     */
    Optional<T> findById(Long id);

    /**
     * @param entity
     * @param loggedInUser
     * @return
     */
    T merge(T entity, User loggedInUser);

    /**
     * @param entity
     * @param loggedInUser
     * @return
     */
    T mergeAndFlush(T entity, User loggedInUser);
}
