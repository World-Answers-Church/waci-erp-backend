package com.waci.erp.shared.dao;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.MetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import com.waci.erp.shared.models.BaseEntity;
import com.waci.erp.shared.models.User;
import com.waci.erp.shared.security.UserDetailsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class BaseDAOImpl<T extends BaseEntity> extends GenericDAOImpl<T, Long> implements BaseDao<T> {

    @Autowired
    public EntityManager entityManager;

    @Autowired
    public JpaAnnotationMetadataUtil mdu;

    public BaseDAOImpl() {
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    protected JPASearchProcessor getSearchProcessor() {
        return new JPASearchProcessor(mdu);
    }
    protected MetadataUtil getMetadataUtil() {
        return new JpaAnnotationMetadataUtil();
    }
    @Override
    public T save(T entity, User loggedInUser) {
        entity.addAuditTrail(loggedInUser);
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        return super.save(entity);
    }

    @Override
    public T save(T entity) {
        User user=UserDetailsContext.getLoggedInUser();
        if(user!=null) {
            entity.addAuditTrail(UserDetailsContext.getLoggedInUser());
        }
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        return super.save(entity);
    }

    @Transactional
    @Override
    public T saveAndFlush(T entity, User loggedInUser) {
        entity.addAuditTrail(loggedInUser);
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        T result = super.save(entity);
        super.flush();
        return result;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(super.find(id));
    }

    @Override
    public T merge(T entity, User loggedInUser) {
        entity.addAuditTrail(loggedInUser);
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        return super.merge(entity);
    }

    @Override
    public T merge(T entity) {
        entity.addAuditTrail(UserDetailsContext.getLoggedInUser());
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        return super.merge(entity);
    }

    @Override
    public T mergeAndFlush(T entity, User loggedInUser) {
        entity.addAuditTrail(loggedInUser);
        if(UserDetailsContext.getLoggedInOrganisation()!=null) {
            entity.setOrganisationCode(UserDetailsContext.getLoggedInOrganisation().getOrganisationCode());
        }
        T result = super.merge(entity);
        super.flush();
        return result;
    }
}
