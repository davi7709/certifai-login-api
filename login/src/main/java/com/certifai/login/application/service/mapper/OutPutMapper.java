package com.certifai.login.application.service.mapper;

import com.certifai.login.domain.UserLogin;
import com.certifai.login.gateway.persistence.jpa.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OutPutMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserLogin userLogin);

    UserLogin toDomain(UserEntity userEntity);
}
