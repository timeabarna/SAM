package com.reaktorlabs.repository;

import com.reaktorlabs.repository.model.Asset;
import com.reaktorlabs.repository.model.SamUser;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AssetRepositoryImpl implements AssetRepository {
    @PersistenceContext(name = "SamPU")
    private EntityManager entityManager;

    @Override
    public void create(Asset entity) {
        entityManager.persist(entity);
    }

    @Override
    public Asset readById(Long id) {
        return entityManager.find(Asset.class, id);
    }

    @Override
    public List<Asset> readAll() {
        TypedQuery<Asset> query = entityManager.createNamedQuery("findAllAssets", Asset.class);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public void update(Asset entity) {
        entityManager.merge(entity);
    }

    @Override
    public void updateById(Long id) {
        entityManager.merge(entityManager.find(Asset.class, id));
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Asset.class, id));
    }

    @Override
    public void delete(Asset entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<Asset> findAssetsByName(String assetName) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.assetName = :assetName", Asset.class);
        return query.setParameter("assetName", assetName).getResultList();
    }

    @Override
    public List<Asset> findAssetsByManufacturer(String manufacturer) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.manufacturer = :manufacturer", Asset.class);
        return query.setParameter("manufacturer", manufacturer).getResultList();
    }

    @Override
    public List<Asset> findAssetsByModel(String model) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.model = :model", Asset.class);
        return query.setParameter("model", model).getResultList();
    }

    @Override
    public List<Asset> findAssetsBySerialNUmber(String serialNumber) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.serialNumber = :serialNumber", Asset.class);
        return query.setParameter("serialNumber", serialNumber).getResultList();
    }

    @Override
    public List<Asset> findAssetsByAssetTag(String assetTag) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.assetTag = :assetTag", Asset.class);
        return query.setParameter("assetTag", assetTag).getResultList();
    }

    @Override
    public List<Asset> findAssetsByOwner(SamUser owner) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.owner = :owner", Asset.class);
        return query.setParameter("owner", owner).getResultList();
    }

    @Override
    public List<Asset> findAssetsByStatus(String status) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.status = :status", Asset.class);
        return query.setParameter("status", status).getResultList();
    }

    @Override
    public List<Asset> findAssetsBySupportEndDate(String supportEndDate) {
        TypedQuery<Asset> query = 
                entityManager.createQuery("SELECT a FROM Asset a WHERE a.supportEndDate = :supportEndDate", Asset.class);
        return query.setParameter("supportEndDate", supportEndDate).getResultList();
    }
    
}
