package com.reaktorlabs.repository;

import com.reaktorlabs.repository.model.Asset;
import com.reaktorlabs.repository.model.SamUser;
import java.util.List;

public interface AssetRepository extends SamCRUDRepository<Long, Asset> {
    List<Asset> findAssetsByName(String assetName);
    List<Asset> findAssetsByManufacturer(String manufacturer);
    List<Asset> findAssetsByModel(String model);
    List<Asset> findAssetsBySerialNUmber(String serialNumber);
    List<Asset> findAssetsByAssetTag(String assetTag);
    List<Asset> findAssetsByOwner(SamUser owner);
    List<Asset> findAssetsByStatus(String status);
    List<Asset> findAssetsBySupportEndDate(String supportEndDate);
}
