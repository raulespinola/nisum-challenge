package org.nisum.challenge.controller;


import io.swagger.annotations.ApiOperation;
import org.nisum.challenge.controller.dto.*;
import org.nisum.challenge.exception.CustomAuthenticateException;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@ApiOperation(value = "Login User", produces = "application/json")
	@GetMapping(value = "/login")
	public ResponseEntity<UserLoginResponseDto> createAuthenticationToken(@RequestBody UserLoginRequestDto authenticationRequest) throws CustomAuthenticateException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		return ResponseEntity.ok(userMapper
				.modelToDto(userService
						.loadUser(authenticationRequest.getUsername())));
	}
	@ApiOperation(value = "Register User - Email Need to be valid forma and Password need " +
			"Min 1 uppercase, 1 lowercase. 1 special character, " +
			"1 character, Min 8 characters", produces = "application/json")
	@PostMapping(value = "/register")
	public ResponseEntity<UserCreateResponseDto> saveUser(@Valid @RequestBody UserCreateRequestDto userRequest)  {
		return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.modelToDto(userService
				.save(userMapper.requestDtoToModel(userRequest))));
	}

	private void authenticate(String username, String password) throws CustomAuthenticateException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new CustomAuthenticateException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new CustomAuthenticateException("INVALID_CREDENTIALS", e);
		}
	}
}