package com.reaktorlabs.sam.repository.repo.samuser;

import com.reaktorlabs.sam.repository.repo.SamCRUDRepository;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import java.util.List;

public interface SamUserRepository extends SamCRUDRepository<Long, SamUser> {
    SamUser findUserByEmail(String email);
    List<SamUser> findAllUsersByFilter(String filter);
    SamUser findUserByName(String name);
    List<SamUser> findAllActiveUsers(String filter);
}
