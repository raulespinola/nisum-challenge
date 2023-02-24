package org.nisum.challenge.mapper;

import org.mapstruct.*;
import org.nisum.challenge.controller.dto.UserCreateRequestDto;
import org.nisum.challenge.controller.dto.UserCreateResponseDto;
import org.nisum.challenge.controller.dto.UserLoginResponseDto;
import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.core.model.UserLoginModel;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserCreateRequestDto userDto);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastLoginDate", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    UserEntity modelToEntity(UserCreationModel userCreationModel);
    UserCreationModel entityToModel(UserEntity userEntity);
    UserCreationModel requestDtoToModel(UserCreateRequestDto userCreateRequestDto);
    UserCreationModel entityToUserCreationModel(UserEntity userEntity);
    UserCreateResponseDto modelToDto(UserCreationModel userCreationModel);
    UserLoginResponseDto modelToDto(UserLoginModel userLoginModel);

}
