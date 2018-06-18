package com.reaktorlabs.repository;

import com.reaktorlabs.repository.model.SamUser;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        TypedQuery<SamUser> query = 
                entityManager.createQuery("SELECT u FROM SamUser u WHERE u.email = :email", SamUser.class);
        return query.setParameter("email", email).getSingleResult();
    }
    
}
