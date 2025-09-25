package com.certifai.login.gateway.persistence.jpa;

import com.certifai.login.application.service.mapper.OutPutMapper;
import com.certifai.login.domain.UserLogin;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserPersistenceJpaAdapter {

    private final UserRepositoryJpa userRepositoryJpa;
    private final OutPutMapper outPutMapper;

    public UserPersistenceJpaAdapter (UserRepositoryJpa userRepositoryJpa, OutPutMapper outPutMapper){
        this.userRepositoryJpa = userRepositoryJpa;
        this.outPutMapper = outPutMapper;
    }

    public UserLogin loadByEmail(String email){
        final var result = userRepositoryJpa.findByEmail(email);
        return outPutMapper.toDomain(result);
    }
}
