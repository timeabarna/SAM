package com.reaktorlabs.sam.repository.repo.asset;

import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.repository.model.asset.AssetStatusEnum;
import com.reaktorlabs.sam.repository.model.asset.Asset_;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.model.samuser.SamUser_;
import com.reaktorlabs.sam.search.asset.AssetFilterObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Stateless
public class AssetRepositoryImpl implements AssetRepository {
    @PersistenceContext(name = "SamPU")
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Asset> assetCriteria;
    private Root<Asset> assetRoot;
    private Join<Asset, SamUser> userJoin;

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
    
    private Predicate makePredicateFromFilter(AssetFilterObject assetFilter) {
        
        criteriaBuilder = entityManager.getCriteriaBuilder();
        assetCriteria = criteriaBuilder.createQuery(Asset.class);
        assetRoot = assetCriteria.from(Asset.class);
        userJoin = assetRoot.join(Asset_.owner);

        List<Predicate> predicates = new ArrayList<>();

        if (assetFilter.getAssetNameFilter() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(assetRoot.get(Asset_.assetName)),
                    "%" + assetFilter.getAssetNameFilter().toLowerCase() + "%"));
        }

        if (assetFilter.getManufacturerFilter() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(assetRoot.get(Asset_.manufacturer)),
                    "%" + assetFilter.getManufacturerFilter().toLowerCase() + "%"));
        }

        if (assetFilter.getModelFilter() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(assetRoot.get(Asset_.model)),
                    "%" + assetFilter.getModelFilter().toLowerCase() + "%"));
        }

        if (assetFilter.getSerialNumberFilter() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(assetRoot.get(Asset_.serialNumber)),
                    "%" + assetFilter.getSerialNumberFilter().toLowerCase() + "%"));
        }

        if (assetFilter.getAssetTagFilter() != null) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(assetRoot.get(Asset_.assetTag)),
                    "%" + assetFilter.getAssetTagFilter().toLowerCase() + "%"));
        }

        if (assetFilter.getOwnerFilter() != null) {
            Path<SamUser> userPath = assetRoot.get(Asset_.owner);
            Predicate ownerDisjunction = criteriaBuilder.disjunction();
            ownerDisjunction.getExpressions().add(criteriaBuilder.like(
                    criteriaBuilder.lower(userPath.get(SamUser_.email)),
                    "%" + assetFilter.getOwnerFilter().toLowerCase() + "%"));
            ownerDisjunction.getExpressions().add(criteriaBuilder.like(
                    criteriaBuilder.lower(userPath.get(SamUser_.firstName)),
                    "%" + assetFilter.getOwnerFilter().toLowerCase() + "%"));
            ownerDisjunction.getExpressions().add(criteriaBuilder.like(
                    criteriaBuilder.lower(userPath.get(SamUser_.lastName)),
                    "%" + assetFilter.getOwnerFilter().toLowerCase() + "%"));
            predicates.add(ownerDisjunction);
        }
        
        if (!assetFilter.getAssetStatusFilters().isEmpty()) {
            Predicate statusDisjunction = criteriaBuilder.disjunction();
            for (String status : assetFilter.getAssetStatusFilters()) {
                statusDisjunction.getExpressions().add(criteriaBuilder.equal(assetRoot.get(Asset_.status), AssetStatusEnum.valueOf(status)));
            }
            predicates.add(statusDisjunction);
        }
        
        if (assetFilter.getSupportEndDateFilter() != null) {
            predicates.add(criteriaBuilder.equal(
                    assetRoot.<Date> get(Asset_.supportEndDate), assetFilter.getSupportEndDateFilter()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    public List<Asset> findAssetByFilter(AssetFilterObject assetFilter) {
        Predicate predicate = makePredicateFromFilter(assetFilter);
        assetCriteria.select(assetRoot);
        assetCriteria.where(predicate);
        List<Asset> assets = entityManager.createQuery(assetCriteria).getResultList();
        return assets;
    }

    @Override
    public List<String> findAllAssetNames() {
        List<String> assetNameList = new LinkedList<>();
        TypedQuery<String> query = entityManager.createQuery("Select DISTINCT an.assetName FROM Asset an", String.class);
        assetNameList = query.getResultList();
        return assetNameList;
    }
}
