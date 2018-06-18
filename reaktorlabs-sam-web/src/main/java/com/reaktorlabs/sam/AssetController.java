package com.reaktorlabs.sam;

import com.reaktorlabs.repository.AssetRepository;
import com.reaktorlabs.repository.model.Asset;
import com.reaktorlabs.repository.model.AssetStatusEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "assetController")
@SessionScoped
//@Stateful
public class AssetController implements Serializable {

    private AssetRepository assetStore;
    private Asset asset = new Asset();
    private List<Asset> assets;
    private List<Asset> collectAssets;

    public AssetController() {
    }
    
    
    
    @Inject
    public AssetController(AssetRepository assetStore) {
        this.assetStore = assetStore;
         this.assets = new ArrayList<>();
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    
    public String search(Asset asset) {
        if(asset.getAssetName() != null) {
        collectAssets = assetStore.findAssetsByName(asset.getAssetName());
         assets.addAll(collectAssets);
       
        }
        if(asset.getManufacturer() != null) {
        collectAssets = assetStore.findAssetsByManufacturer(asset.getManufacturer());
         assets.addAll(collectAssets);
        }
        if(asset.getModel() != null) {
        collectAssets = assetStore.findAssetsByModel(asset.getModel());
         assets.addAll(collectAssets);
        }
        if(asset.getSerialNumber() != null) {
        collectAssets = assetStore.findAssetsBySerialNUmber(asset.getSerialNumber());
         assets.addAll(collectAssets);
        }
        if(asset.getAssetTag() != null) {
        collectAssets = assetStore.findAssetsByAssetTag(asset.getAssetTag());
         assets.addAll(collectAssets);
        }
        if(asset.getOwner() != null) {
        //collectAssets = assetStore.findAssetsByOwner(asset.getOwner());
         //assets.addAll(collectAssets);
        }
        if(asset.getStatus() != null) {
        collectAssets = assetStore.findAssetsByStatus(asset.getStatus().toString());
         assets.addAll(collectAssets);
        }
        if(asset.getSupportEndDate() != null) {
        collectAssets = assetStore.findAssetsBySupportEndDate(asset.getSupportEndDate().toString());
         assets.addAll(collectAssets);
        }
       //return assets;
        setSessionMapValue("searchFilter", assets);
       return "list-asset.xhtml";
        
    }
    
    public void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    
    public List<AssetStatusEnum> collectAssetStatus(List<AssetStatusEnum> statusEnum) {
        List<AssetStatusEnum> status = new ArrayList<>();
        status.addAll(statusEnum);
        return status;
    }
}
