package com.reaktorlabs.samview.list;

import com.reaktorlabs.sam.repository.repo.asset.AssetRepository;
import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.logic.util.ExcelWriter;
import com.reaktorlabs.sam.search.asset.AssetFilterObject;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listController")
@ViewScoped
public class ListController implements Serializable {

    private AssetRepository assetRepository;
    private AssetFilterObject assetFilter;
    private List<Asset> assets;
    private List<Asset> filteredAssets;
    private Boolean allSelected = false;
    private Map<Integer, Boolean> listIndexToSelection = new HashMap<>();

    @Inject
    public ListController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @PostConstruct
    public void init() {
        assetFilter = (AssetFilterObject) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("searchFilter");
        assets = findAssets(assetFilter);
    }

    public int size() {
        return assets.size();
    }

    public Asset getAssetByIndex(int index) {
        return assets.get(index);
    }

    public void export(List<Asset> filteredAssets) throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset();
        ec.setResponseContentType("application/vnd.ms-excel");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"sam_export.xlsx\"");

        OutputStream output = ec.getResponseOutputStream();

        byte[] workbookContent = ExcelWriter.excelWriter(filteredAssets);
        try {
            output.write(workbookContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fc.responseComplete();

    }

    public List<Asset> findAssets(AssetFilterObject assetFilter) {
        assets = new ArrayList<>();
        assets.addAll(assetRepository.findAssetByFilter(assetFilter));
        return assets;
    }

    private void filterAssets() {
        filteredAssets = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> entry : listIndexToSelection.entrySet()) {
            if (entry.getValue()) {
                filteredAssets.add(assets.get(entry.getKey()));
            }
        }
        listIndexToSelection.clear();
        allSelected = false;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public AssetFilterObject getAssetFilter() {
        filterAssets();
        return assetFilter;
    }

    public void setAssetFilter(AssetFilterObject assetFilter) {
        this.assetFilter = assetFilter;
    }

    public List<Asset> getFilteredAssets() {
        filterAssets();
        return filteredAssets;
    }

    public void setFilteredAssets(List<Asset> filteredAssets) {
        this.filteredAssets = filteredAssets;
    }

    public Map<Integer, Boolean> getListIndexToSelection() {
        return listIndexToSelection;
    }

    public void setListIndexToSelection(Map<Integer, Boolean> listIndexToSelection) {
        this.listIndexToSelection = listIndexToSelection;
    }

    public Boolean getAllSelected() {
        return allSelected;
    }

    public void setAllSelected(Boolean allSelected) {
        this.allSelected = allSelected;
    }
}
