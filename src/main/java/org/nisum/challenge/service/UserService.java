package org.nisum.challenge.service;

import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequest);
}
