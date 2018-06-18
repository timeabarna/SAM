package com.reaktorlabs.samview.decom;

import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.repository.model.asset.AssetStatusEnum;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.repo.asset.AssetRepository;
import com.reaktorlabs.sam.repository.repo.samuser.SamUserRepository;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "decommController")
@ViewScoped
public class DecommController implements Serializable {
    private AssetRepository assetRepository;
    private SamUserRepository userRepository;
    
    @Inject
    public DecommController(AssetRepository assetRepository, SamUserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }
    
    public void decomm(List<Asset> filteredAssets) {
        SamUser user = userRepository.findUserByEmail("decomm@dummy.com");
            for (Asset asset : filteredAssets) {
                asset.setOwner(user);
                asset.setStatus(AssetStatusEnum.DECOMISSIONED);
                assetRepository.update(asset);
            }
    }
}
