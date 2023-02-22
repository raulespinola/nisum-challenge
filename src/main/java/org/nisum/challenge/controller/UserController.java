package org.nisum.challenge.controller;

import io.swagger.annotations.ApiOperation;
import org.nisum.challenge.controller.dto.UserRequestDto;
import org.nisum.challenge.controller.dto.UserResponseDto;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController(value = "api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserMapper itemMapper;

    @PostMapping(value = "/",  produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create User", produces = "application/json")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequest){

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto = userServiceImpl.createUser(userRequest);

        return ResponseEntity.ok(userResponseDto);
    }

}
