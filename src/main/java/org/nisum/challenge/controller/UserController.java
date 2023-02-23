package org.nisum.challenge.controller;

import io.swagger.annotations.ApiOperation;
import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/user",  produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create User", produces = "application/json")
    public ResponseEntity<UserResponseDto> createUser( @Valid @RequestBody UserRequestDto userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.modelToDto(userService
                .createUser(userMapper.requestDtoToModel(userRequest))));
    }

}
