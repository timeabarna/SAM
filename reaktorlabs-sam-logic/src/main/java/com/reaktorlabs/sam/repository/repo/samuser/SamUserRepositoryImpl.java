package com.reaktorlabs.sam.repository.repo.samuser;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.model.samuser.SamUserRoleEnum;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class SamUserRepositoryImpl implements SamUserRepository {

    @PersistenceContext(name = "SamPu")
    private EntityManager entityManager;

    @Override
    public void create(SamUser entity) {
        entityManager.persist(entity);
    }

    @Override
    public SamUser readById(Long id) {
        return entityManager.find(SamUser.class, id);
    }

    @Override
    public List<SamUser> readAll() {
        TypedQuery<SamUser> query = entityManager.createNamedQuery("findAllUser", SamUser.class);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public void update(SamUser entity) {
        entityManager.merge(entity);
    }

    @Override
    public void updateById(Long id) {
        entityManager.merge(entityManager.find(SamUser.class, id));
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(SamUser.class, id));
    }

    @Override
    public void delete(SamUser entity) {
        entityManager.remove(entity);
    }

    @Override
    public SamUser findUserByEmail(String email) {
        TypedQuery<SamUser> query
                = entityManager.createQuery("SELECT u FROM SamUser u WHERE u.email LIKE :email", SamUser.class);
        SamUser user;
        try {
            user = query.setParameter("email", "%" + email + "%").getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<SamUser> findAllUsersByFilter(String filter) {
        filter = filter.toLowerCase();
        List<SamUser> resultSet = new LinkedList<>();
        TypedQuery<SamUser> query
                = entityManager.createQuery("SELECT u FROM SamUser u WHERE (LOWER(u.email) LIKE :email) OR (LOWER(u.firstName) LIKE :firstName) OR (LOWER(u.lastName) LIKE :lastName)", SamUser.class);
        resultSet.addAll(query.setParameter("email", "%" + filter + "%").setParameter("firstName", "%" + filter + "%").setParameter("lastName", "%" + filter + "%").getResultList());
        return resultSet;
    }
    
    @Override
    public List<SamUser> findAllActiveUsers(String filter) {
        filter = filter.toLowerCase();
        List<SamUser> resultSet = new LinkedList<>();
        TypedQuery<SamUser> query
                = entityManager.createQuery("SELECT u FROM SamUser u WHERE ((u.userRole NOT LIKE :userRole) AND ((LOWER(u.email) LIKE :email) OR (LOWER(u.firstName) LIKE :firstName) OR (LOWER(u.lastName) LIKE :lastName)))", SamUser.class);
        resultSet.addAll(query.setParameter("userRole", SamUserRoleEnum.PASSIVE).setParameter("email", "%" + filter + "%").setParameter("firstName", "%" + filter + "%").setParameter("lastName", "%" + filter + "%").getResultList());
        return resultSet;
    }
    
    @Override
    public SamUser findUserByName(String name) {
        name = name.toLowerCase();
        TypedQuery<SamUser> query
                = entityManager.createQuery("SELECT u FROM SamUser u WHERE LOWER(u.firstName) LIKE :firstName OR LOWER(u.lastName) = :lastName", SamUser.class);
        SamUser user;
        try {
            user = query.setParameter("firstName", "%" + name + "%")
                    .setParameter("lastName", "%" + name + "%").getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
}
