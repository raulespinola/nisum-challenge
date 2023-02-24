package org.nisum.challenge.service;

import org.nisum.challenge.config.JwtTokenUtil;
import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.core.model.UserLoginModel;
import org.nisum.challenge.mapper.UserEntityMapper;
import org.nisum.challenge.mapper.UserMapper;
import org.nisum.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserEntityMapper userEntityMapper;



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCreationModel user = userMapper.entityToUserCreationModel(userRepository.findByUsername(username));
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());

	}

	public UserLoginModel loadUser(String username){
		UserDetails userDetails = loadUserByUsername(username);
		String token = jwtTokenUtil.generateToken(userDetails);
		return UserLoginModel.builder()
				.token(token)
				.username(userDetails.getUsername())
				.expirationDateForToken(jwtTokenUtil.getExpirationDateFromToken(token))
				.build();
	}
	
	public UserCreationModel save(UserCreationModel user) {
		return userMapper.entityToUserCreationModel(userRepository
				.save(userEntityMapper.modelToEntity(user,  bcryptEncoder.encode(user.getPassword()))));
	}
}