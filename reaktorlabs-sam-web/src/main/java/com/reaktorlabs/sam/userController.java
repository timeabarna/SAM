package com.reaktorlabs.sam;

import com.reaktorlabs.repository.SamUserRepository;
import com.reaktorlabs.repository.model.SamUser;
import com.reaktorlabs.sam.util.HashUtility;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named(value = "userController")
@RequestScoped
public class UserController implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    private SamUserRepository userStore;
    private SamUser user = new SamUser();
   
    @Inject
    private HttpServletRequest servletRequest;

    
    @Inject
    public UserController(SamUserRepository userStore) {
        this.userStore = userStore;
    }

    public SamUser getUser() {
        return user;
    }

    public void setUser(SamUser user) {
        this.user = user;
    }
    
    public String login(SamUser user) {
        try {
            servletRequest.getSession();
            servletRequest.login(user.getEmail(), user.getPassword());
            return "search-asset.xhtml";
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
            return "error.xhtml";
        }
    }

}
