package org.nisum.challenge.service;

import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.core.model.UserModel;

public interface UserService {
    UserModel createUser(UserModel userRequest);
}
