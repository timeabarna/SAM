package com.reaktorlabs.samview.add.assetowner;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.repo.samuser.SamUserRepository;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "addAssetOwnerController")
@ViewScoped
public class AddAssetOwnerController implements Serializable {
    private SamUserRepository userRepository;
    private SamUser user = new SamUser();
    private List<SamUser> ownerList = new LinkedList<>();
    private String searchText;
   
    
    @Inject
    public AddAssetOwnerController(SamUserRepository userStore) {
        this.userRepository = userStore;
    }
    
    public void collectOwners(String searchText) {
        ownerList = userRepository.findAllActiveUsers(searchText);
    }

    public SamUser identifyOwner(String searchText) {
        if (searchText.contains("@")) {
            user = userRepository.findUserByEmail(searchText);
        } else {
            user = userRepository.findUserByName(searchText);
        }
        return user;
    }
    
    public int size() {
        return ownerList.size();
    }
    
    public void clear() {
        ownerList.clear();
    }
    
    public SamUser getUser() {
        return user;
    }

    public void setUser(SamUser user) {
        this.user = user;
    }

    public List<SamUser> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<SamUser> ownerList) {
        this.ownerList = ownerList;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
