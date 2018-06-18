package com.reaktorlabs.samview.add.asset;

import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.repository.repo.asset.AssetRepository;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addAssetController")
@ViewScoped
public class AddAssetController implements Serializable {

    private final static String[] EDIT_MODE_FIELDS = {"manufacturer", "model", "serialNumber",
        "assetTag", "location", "owner", "supportEndDate"};
    private AssetRepository assetRepository;
    private Asset assetToAdd = new Asset();
    private List<String> assetNameList;
    private List<Asset> assetsToAddList = new LinkedList<>();
    private Map<String, Boolean> inputFieldToEdtitMode;
    private String saveButtonId;
    private String editButtonId;
    private String previousEditButtonId;

    @Inject
    public AddAssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @PostConstruct
    public void addAssetControllerInit() {
        gatherAssetNames();
        initializeEditModeMap();
    }

    public int size() {
        return assetsToAddList.size();
    }

    public Asset getAssetByIndex(int index) {
        return assetsToAddList.get(index);
    }
    
    public void collectAssets(Asset asset) {
        assetsToAddList.add(asset);
        assetToAdd = new Asset();
    }

    public void addAsset(List<Asset> assetsToAddList) {
        for (Asset asset : assetsToAddList) {
        Asset newAsset = new Asset();
        newAsset.setAssetName(asset.getAssetName());
        newAsset.setAssetTag(asset.getAssetTag());
        newAsset.setLocation(asset.getLocation());
        newAsset.setManufacturer(asset.getManufacturer());
        newAsset.setModel(asset.getModel());
        newAsset.setOwner(asset.getOwner());
        newAsset.setSerialNumber(asset.getSerialNumber());
        newAsset.setStatus(asset.getStatus());
        newAsset.setSupportEndDate(asset.getSupportEndDate());
        assetRepository.create(newAsset);
        }
        assetsToAddList.clear();
    }

    public void initializeEditModeMap() {
        inputFieldToEdtitMode = new TreeMap<>();
        for (int index = 0; index < EDIT_MODE_FIELDS.length; index++) {
            inputFieldToEdtitMode.put(EDIT_MODE_FIELDS[index], Boolean.FALSE);
        }
    }

    public List<String> gatherAssetNames() {
        assetNameList = new LinkedList<>();
        assetNameList = assetRepository.findAllAssetNames();
        return assetNameList;
    }

    public Asset getAssetToAdd() {
        return assetToAdd;
    }

    public void setAssetToAdd(Asset assetToAdd) {
        this.assetToAdd = assetToAdd;
    }

    public List<String> getAssetNameList() {
        return assetNameList;
    }

    public void setAssetNameList(List<String> assetNameList) {
        this.assetNameList = assetNameList;
    }

    public void edit() {
        if(previousEditButtonId != null) {
        inputFieldToEdtitMode.put(previousEditButtonId, Boolean.FALSE);
        }
        inputFieldToEdtitMode.put(editButtonId, Boolean.TRUE);
    }

    public void collectEditButtonId(ActionEvent event) {
        previousEditButtonId = editButtonId;
        editButtonId = event.getComponent().getId();
        editButtonId = editButtonId.replace("Edit", "");
    }

    public void save() {
        inputFieldToEdtitMode.put(saveButtonId, Boolean.FALSE);
    }

    public void collectSaveButtonId(ActionEvent event) {
        saveButtonId = event.getComponent().getId();
        saveButtonId = saveButtonId.replace("Save", "");
    }

    public List<Asset> getAssetsToAddList() {
        return assetsToAddList;
    }

    public void setAssetsToAddList(List<Asset> assetsToAddList) {
        this.assetsToAddList = assetsToAddList;
    }

    public Map<String, Boolean> getInputFieldToEdtitMode() {
        return inputFieldToEdtitMode;
    }

    public void setInputFieldToEdtitMode(Map<String, Boolean> inputFieldToEdtitMode) {
        this.inputFieldToEdtitMode = inputFieldToEdtitMode;
    }
}
