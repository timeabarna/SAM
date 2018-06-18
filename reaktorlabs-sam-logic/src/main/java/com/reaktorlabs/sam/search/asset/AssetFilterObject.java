package com.reaktorlabs.sam.search.asset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetFilterObject implements Serializable {
    
    private String assetNameFilter;
    private String manufacturerFilter;
    private String modelFilter;
    private String serialNumberFilter;
    private String assetTagFilter;
    private String ownerFilter;
    private List<String> assetStatusFilters;
    private Date supportEndDateFilter;

    public AssetFilterObject() {
        this.assetStatusFilters = new ArrayList<>();
    }

    public String getAssetNameFilter() {
        return assetNameFilter;
    }

    public void setAssetNameFilter(String assetNameFilter) {
        this.assetNameFilter = assetNameFilter;
    }

    public String getManufacturerFilter() {
        return manufacturerFilter;
    }

    public void setManufacturerFilter(String manufacturerFilter) {
        this.manufacturerFilter = manufacturerFilter;
    }

    public String getModelFilter() {
        return modelFilter;
    }

    public void setModelFilter(String modelFilter) {
        this.modelFilter = modelFilter;
    }

    public String getSerialNumberFilter() {
        return serialNumberFilter;
    }

    public void setSerialNumberFilter(String serialNumberFilter) {
        this.serialNumberFilter = serialNumberFilter;
    }

    public String getAssetTagFilter() {
        return assetTagFilter;
    }

    public void setAssetTagFilter(String assetTagFilter) {
        this.assetTagFilter = assetTagFilter;
    }

    public String getOwnerFilter() {
        return ownerFilter;
    }

    public void setOwnerFilter(String ownerFilter) {
        this.ownerFilter = ownerFilter;
    }

    public List<String> getAssetStatusFilters() {
        return assetStatusFilters;
    }

    public void setAssetStatusFilters(List<String> assetStatusFilters) {
        this.assetStatusFilters = assetStatusFilters;
    }

    public Date getSupportEndDateFilter() {
        return supportEndDateFilter;
    }

    public void setSupportEndDateFilter(Date supportEndDateFilter) {
        this.supportEndDateFilter = supportEndDateFilter;
    }
}
