package com.reaktorlabs.sam.repository.repo.asset;

import com.reaktorlabs.sam.repository.repo.SamCRUDRepository;
import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.search.asset.AssetFilterObject;
import java.util.List;

public interface AssetRepository extends SamCRUDRepository<Long, Asset> {
    List<Asset> findAssetByFilter(AssetFilterObject assetFilter);
    List<String> findAllAssetNames();
}
