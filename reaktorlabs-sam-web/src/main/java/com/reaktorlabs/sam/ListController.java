package com.reaktorlabs.sam;

import com.reaktorlabs.repository.AssetRepository;
import com.reaktorlabs.repository.model.Asset;
import com.reaktorlabs.sam.util.ExcelWriter;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "listController")
@ViewScoped
public class ListController implements Serializable {

   //private AssetRepository assetRepository;
    private List<Asset> assets;

    /*@Inject
    public ListController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }*/
    
    
    @PostConstruct
    public void init() {
      assets = (List) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchFilter");
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public int size() {
        return assets.size();
    }
    
    public Asset getAssetByIndex(int index) {
        return assets.get(index);
    }
    
    public void export() {
        ExcelWriter.excelWriter(assets);
    }
}
