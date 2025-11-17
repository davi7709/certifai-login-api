package com.certifai.login.gateway.presentation.http;

import com.certifai.login.application.port.in.AuthUser;
import com.certifai.login.application.service.mapper.InputMapper;
import com.certifai.login.domain.UserLogin;
import com.certifai.login.gateway.presentation.http.resources.UserLoginRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserLoginController {

    private final AuthUser authUser;
    private final InputMapper inputMapper;

    public UserLoginController (AuthUser authUser, InputMapper inputMapper){
        this.authUser = authUser;
        this.inputMapper = inputMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestBody userLoginRequestBody){
        final var authenticated = authUser.login(inputMapper.toDomain(userLoginRequestBody));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login sucess");
    }
}
