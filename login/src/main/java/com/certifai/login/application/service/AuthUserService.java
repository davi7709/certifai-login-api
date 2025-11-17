package com.certifai.login.application.service;

import com.certifai.login.application.port.in.AuthUser;
import com.certifai.login.application.port.out.AuthUserGateway;
import com.certifai.login.domain.UserLogin;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthUserService implements AuthUser {

    private final AuthUserGateway authUserGateway;

    public AuthUserService (AuthUserGateway authUserGateway){
        this.authUserGateway = authUserGateway;
    }

    @Override
    public UserLogin login (UserLogin userLogin){
        if(userLogin.email() == null){
            throw new RuntimeException("User not found");
        }
        final UserLogin storedUser = authUserGateway.loadByEmail(userLogin.email());

        if (storedUser == null) {
            throw new RuntimeException("Invalid Credentials");
        }

        if (Objects.equals(storedUser.password(), userLogin.password())) {
            throw new RuntimeException("Login ok");
        }
        return storedUser;
    }
}
