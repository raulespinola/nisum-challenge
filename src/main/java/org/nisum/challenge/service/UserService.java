package org.nisum.challenge.service;

import org.nisum.challenge.core.model.UserCreationModel;
import org.nisum.challenge.core.model.UserModel;
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


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCreationModel user = userMapper.entityToUserCreationModel(userRepository.findByUsername(username));
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserCreationModel save(UserModel user) {
		return userMapper.entityToUserCreationModel(userRepository
				.save(userMapper.modelToEntity(user)));
	}
}