package com.certifai.login.application.port.in;

import com.certifai.login.domain.UserLogin;

public interface AuthUser {
    UserLogin login(UserLogin userLogin);
}
