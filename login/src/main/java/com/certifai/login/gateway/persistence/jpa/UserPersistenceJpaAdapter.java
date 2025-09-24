package com.certifai.login.gateway.persistence.jpa;

import com.certifai.login.domain.UserLogin;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserPersistenceJpaAdapter {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserPersistenceJpaAdapter (UserRepositoryJpa userRepositoryJpa){
        this.userRepositoryJpa = userRepositoryJpa;
    }

    public Optional<UserLogin> loadByEmail(String email){
        return userRepositoryJpa.findByEmail(email);
    }
}
