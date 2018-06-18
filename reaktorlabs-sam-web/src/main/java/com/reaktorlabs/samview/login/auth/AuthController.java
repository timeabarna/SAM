package com.reaktorlabs.samview.login.auth;

import com.reaktorlabs.sam.logic.auth.Authentication;
import com.reaktorlabs.sam.logic.auth.AuthenticationServiceQualifier;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.model.samuser.SamUserRoleEnum;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Named(value = "authController")
@RequestScoped
public class AuthController implements SamViewAuthentication, Serializable{
    
    @Context
    private SecurityContext securityContext;
    
    private Authentication authService;

    public AuthController() {
    }

    @Inject
    public AuthController(@AuthenticationServiceQualifier Authentication authService) {
        this.authService = authService;
    }
    
    @Override
    public String login(SamUser user) {
        String loginResponse = authService.login(user);
       if("OK".equals(loginResponse)) {
           return "resources/search-asset.xhtml" + "?faces-redirect=true";
       } else if("RoleERROR".equals(loginResponse)){
           throw new IllegalArgumentException("Users with passive or store role are not allowed to log into the system.");
       } else if("ERROR".equals(loginResponse)){

           throw new IllegalArgumentException("User name or password incorrect. Please check and try again.");
       }
       throw new IllegalArgumentException("Login failed.");
    }

    @Override
    public void logout() {
        authService.logout();
    }

    @Override
    public void register(SamUser user) {
        authService.register(user);
    }

    @Override
    public void registerUserWithRole(SamUser user, SamUserRoleEnum role) {
        authService.registerUserWithRole(user, role);
    }
}
