package org.nisum.challenge.service;

import org.nisum.challenge.core.entity.UserEntity;
import org.nisum.challenge.core.model.UserModel;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserModel createUser(UserModel userRequest) {
        return userMapper.entityToModel(userRepository.save(userMapper.modelToEntity(userRequest)));
    }
}
