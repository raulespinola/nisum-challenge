package org.nisum.challenge.service;

import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.core.model.UserModel;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserCreationModel createUser(UserModel userRequest) {
        return userMapper.entityToUserCreationModel(userRepository
                .save(userMapper.modelToEntity(userRequest)));
    }
}
