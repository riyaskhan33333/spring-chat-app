package com.livechat.chat.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.livechat.chat.entity.UserEntity;
import com.livechat.chat.repository.UserEntityRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserEntityRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity=userRepository.findUserEntityByUserName(username).orElseThrow(
				()->new UsernameNotFoundException("there is no user for this username!"));
		
		
		return new User(userEntity.getUserName(),userEntity.getPassword(),Collections.singleton(
				new SimpleGrantedAuthority("ROLE_"+userEntity.getRole())));
	}

}
