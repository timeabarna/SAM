package com.reaktorlabs.samview.util;

import com.reaktorlabs.sam.repository.model.asset.AssetStatusEnum;
import com.reaktorlabs.sam.repository.model.samuser.SamUserRoleEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "enumReaderBean")
@ApplicationScoped
public class EnumReaderController {

    private List<String> assetStatusForSearch;
    private List<String> assetStatusForAddAsset
            = Arrays.asList(AssetStatusEnum.ASSIGNED.name(),
                    AssetStatusEnum.STORED.name(),
                    AssetStatusEnum.MITIGATED.name());
    private List<String> userRoleStatus;

    public EnumReaderController() {
    }

    private List<String> createAssetStatusForSearch() {
        assetStatusForSearch = new ArrayList<>();
        for (AssetStatusEnum statusEnum : AssetStatusEnum.values()) {
            assetStatusForSearch.add(statusEnum.name());
        }
        return assetStatusForSearch;
    }

    private List<String> createUserRoleStatus() {
        userRoleStatus = new ArrayList<>();
        for (SamUserRoleEnum userRole : SamUserRoleEnum.values()) {
            assetStatusForSearch.add(userRole.name());
        }
        return assetStatusForSearch;
    }

    public List<String> getAssetStatusForSearch() {
        if (assetStatusForSearch == null) {
            return createAssetStatusForSearch();
        } else {
            return assetStatusForSearch;
        }
    }

    public void setAssetStatusForSearch(List<String> assetStatusForSearch) {
        this.assetStatusForSearch = assetStatusForSearch;
    }

    public List<String> getAssetStatusForAddAsset() {
        return assetStatusForAddAsset;
    }

    public void setAssetStatusForAddAsset(List<String> assetStatusForAddAsset) {
        this.assetStatusForAddAsset = assetStatusForAddAsset;
    }

    public List<String> getUserRoleStatus() {
        if (assetStatusForSearch == null) {
            return createUserRoleStatus();
        } else {
            return userRoleStatus;
        }
    }

    public void setUserRoleStatus(List<String> userRoleStatus) {
        this.userRoleStatus = userRoleStatus;
    }

}
