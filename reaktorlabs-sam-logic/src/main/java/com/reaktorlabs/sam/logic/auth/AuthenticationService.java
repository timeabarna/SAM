package com.reaktorlabs.sam.logic.auth;

import com.reaktorlabs.sam.logic.util.HashUtility;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.model.samuser.SamUserRoleEnum;
import com.reaktorlabs.sam.repository.repo.SamCRUDRepository;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Stateless
@AuthenticationServiceQualifier
public class AuthenticationService implements Authentication {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class.getName());

    private SamCRUDRepository<Long, SamUser> samUserRepository;
    private HttpServletRequest servletRequest;

    public AuthenticationService() {
    }

    @Inject
    public AuthenticationService(SamCRUDRepository<Long, SamUser> samUserRepository, HttpServletRequest servletRequest) {
        this.samUserRepository = samUserRepository;
        this.servletRequest = servletRequest;
    }

    @Override
    public String login(SamUser user) {
        try {
            servletRequest.getSession();
            servletRequest.login(user.getEmail(), user.getPassword());
            if (servletRequest.isUserInRole("PASSIVE") || servletRequest.isUserInRole("STORE")) {
                logout();
                return "RoleERROR";
            }
            return "OK";
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return "ERROR";
    }

    @Override
    public void logout() {
        try {
            servletRequest.getSession();
            servletRequest.logout();
        } catch (ServletException ex) {
            LOGGER.warning(ex.getMessage());
        }
    }

    @Override
    public void register(SamUser user) {
        if (samUserRepository.readById(user.getId()) == null) {
            final SamUserRoleEnum defaultRole = SamUserRoleEnum.OWNER;
            user.setUserRole(defaultRole);
            registerUserWithRole(user, defaultRole);
        } else {
            throw new IllegalStateException("User already exists: " + user.getEmail());
        }
    }

    @Override
    public void registerUserWithRole(SamUser user, SamUserRoleEnum role) {
        final SamUser newUser = new SamUser();
        final String password = user.getPassword();
        final String encryptedPassword = HashUtility.encryptSHA512(password);
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encryptedPassword);
        newUser.setUserRole(role);
        samUserRepository.create(newUser);
    }
}
