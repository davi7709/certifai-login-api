package com.certifai.login.application.port.out;

import com.certifai.login.domain.UserLogin;

public interface AuthUserGateway {
    UserLogin loadByEmail(String email);
}
