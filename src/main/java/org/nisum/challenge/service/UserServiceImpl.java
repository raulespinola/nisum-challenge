package org.nisum.challenge.service;

import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto userRequest) {

        UserEntity userEntity = new UserEntity();
        UserEntity user = userRepository.save(userEntity);


    }
}
