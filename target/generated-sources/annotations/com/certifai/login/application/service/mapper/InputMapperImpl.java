package com.certifai.login.application.service.mapper;

import com.certifai.login.domain.UserLogin;
import com.certifai.login.gateway.presentation.http.resources.UserLoginRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-09T23:54:51-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class InputMapperImpl implements InputMapper {

    @Override
    public UserLogin toDomain(UserLoginRequestBody userLoginRequestBody) {
        if ( userLoginRequestBody == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = userLoginRequestBody.email();
        password = userLoginRequestBody.password();

        UserLogin userLogin = new UserLogin( email, password );

        return userLogin;
    }
}
