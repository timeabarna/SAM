package com.reaktorlabs.samview.login;

import com.reaktorlabs.sam.repository.repo.samuser.SamUserRepository;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

@Named(value = "userController")
@ViewScoped
public class UserController implements Serializable {
    private SamUserRepository userRepository;
    private SamUser user = new SamUser();
   
    @Inject
    public UserController(SamUserRepository userStore) {
        this.userRepository = userStore;
    }
    
    public SamUser getUser() {
        return user;
    }

    public void setUser(SamUser user) {
        this.user = user;
    }
}
