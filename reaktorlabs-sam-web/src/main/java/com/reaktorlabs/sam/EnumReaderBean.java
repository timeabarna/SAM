package com.reaktorlabs.sam;

import com.reaktorlabs.repository.model.AssetStatusEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named(value = "enumReaderBean")
@ApplicationScoped
public class EnumReaderBean {
    
    private List<String> assetStatus;

    public EnumReaderBean() {
    }
    
    @PostConstruct
    private void initEnumReader() {
        assetStatus = new ArrayList<>();
        for (AssetStatusEnum statusEnum : AssetStatusEnum.values()) {
            assetStatus.add(statusEnum.name());
        }
    }

    public List<String> getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(List<String> assetStatus) {
        this.assetStatus = assetStatus;
    }
 
}
