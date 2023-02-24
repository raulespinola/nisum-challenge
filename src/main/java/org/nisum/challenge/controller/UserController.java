package org.nisum.challenge.controller;


import io.swagger.annotations.ApiOperation;
import org.nisum.challenge.config.JwtTokenUtil;
import org.nisum.challenge.controller.dto.*;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserService userDetailsService;
	@Autowired
	private UserMapper userMapper;

	@ApiOperation(value = "Login User", produces = "application/json")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	@ApiOperation(value = "Register User - Email Need to be valid forma and Password need " +
			"Min 1 uppercase, 1 lowercase. 1 special character, " +
			"1 character, Min 8 characters", produces = "application/json")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequest)  {
		return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.modelToDto(userDetailsService
				.save(userMapper.requestDtoToModel(userRequest))));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}