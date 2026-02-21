package com.livechat.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livechat.chat.repository.UserEntityRepository;

@RestController
@CrossOrigin("*")
public class UserControllerTwo {
   @Autowired
   UserEntityRepository userEntityRepository;
	@GetMapping("/users/exists")
	public boolean isUserThere(@RequestParam String username)
	{
		return userEntityRepository.existsByUserName(username);
	}
}
