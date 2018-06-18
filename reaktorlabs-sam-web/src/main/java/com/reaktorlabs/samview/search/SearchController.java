package com.reaktorlabs.samview.search;

import com.reaktorlabs.sam.search.asset.AssetFilterObject;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@Named(value = "searchController")
@SessionScoped
public class SearchController implements Serializable {
    private AssetFilterObject assetFilter;

    public SearchController() {
        this.assetFilter = new AssetFilterObject();
    }
    
    public String search(AssetFilterObject assetFilter) {
        setSessionMapValue("searchFilter", assetFilter);
        return "list-asset.xhtml"  + "?faces-redirect=true";
    }
    
    public void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public AssetFilterObject getAssetFilter() {
        return assetFilter;
    }

    public void setAssetFilter(AssetFilterObject assetFilter) {
        this.assetFilter = assetFilter;
    }

}
