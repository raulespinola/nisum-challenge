package org.nisum.challenge.mapper;

import org.mapstruct.Mapper;
import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.core.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserRequestDto userDto);
    UserModel dtoToModel(UserRequestDto userRequestDto);
    UserResponseDto modelToDto (UserModel userModel);
}
