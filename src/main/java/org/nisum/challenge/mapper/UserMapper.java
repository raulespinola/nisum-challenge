package org.nisum.challenge.mapper;

import org.mapstruct.Mapper;
import org.nisum.challenge.controller.dto.PhoneDto;
import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.core.entity.PhoneEntity;
import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.core.model.PhoneModel;
import org.nisum.challenge.core.model.UserModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserRequestDto userDto);
    UserEntity modelToEntity(UserModel userModel);
    UserModel entityToModel(UserEntity userEntity);
    UserModel dtoToModel(UserRequestDto userRequestDto);
    UserResponseDto modelToDto (UserModel userModel);
}
