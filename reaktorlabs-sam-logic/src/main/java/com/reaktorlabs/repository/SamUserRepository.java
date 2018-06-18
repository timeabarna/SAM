package com.reaktorlabs.repository;

import com.reaktorlabs.repository.model.SamUser;

public interface SamUserRepository extends SamCRUDRepository<Long, SamUser> {
    SamUser findUserByEmail(String email);
}
