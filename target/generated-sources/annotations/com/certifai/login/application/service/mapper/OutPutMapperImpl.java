package com.certifai.login.application.service.mapper;

import com.certifai.login.domain.UserLogin;
import com.certifai.login.gateway.persistence.jpa.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T21:23:42-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class OutPutMapperImpl implements OutPutMapper {

    @Override
    public UserEntity toEntity(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( userLogin.email() );

        return userEntity;
    }

    @Override
    public UserLogin toDomain(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String email = null;

        email = userEntity.getEmail();

        String password = null;

        UserLogin userLogin = new UserLogin( email, password );

        return userLogin;
    }
}
