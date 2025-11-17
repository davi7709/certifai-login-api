package com.certifai.login.application.service.mapper;

import com.certifai.login.domain.UserLogin;
import com.certifai.login.gateway.presentation.http.resources.UserLoginRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InputMapper {
    UserLogin toDomain(UserLoginRequestBody userLoginRequestBody);
}
