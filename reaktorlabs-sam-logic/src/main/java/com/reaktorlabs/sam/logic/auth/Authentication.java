package com.reaktorlabs.sam.logic.auth;

import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import com.reaktorlabs.sam.repository.model.samuser.SamUserRoleEnum;

public interface Authentication {
    String login(SamUser user);
    void logout();
    void register(SamUser user);
    void registerUserWithRole(SamUser user, SamUserRoleEnum role);

}
