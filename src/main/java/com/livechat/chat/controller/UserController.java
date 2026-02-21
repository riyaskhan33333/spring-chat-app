package com.livechat.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.livechat.chat.entity.UserEntity;
import com.livechat.chat.repository.UserEntityRepository;



@Controller
@CrossOrigin("*")
public class UserController {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserEntityRepository userEntityRepository;
	
	@GetMapping("/login")
	public String openLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/register")
	public String openRegisterPage(Model model) {
		model.addAttribute("user",new UserEntity());
		return "register";
	}
	@PostMapping("/users/save")
	public String saveUser(@ModelAttribute UserEntity user,Model model,RedirectAttributes redirectAttributes) throws RuntimeException{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			if(!userEntityRepository.existsByUserName(user.getUserName())) {
				userEntityRepository.save(user);
			}
			else {
				throw new RuntimeException("username already belong to someone!");
			}
		}
		catch(RuntimeException e) {
			redirectAttributes.addFlashAttribute("error",e.getMessage());
			return "redirect:/register";
		}
		
	    return "redirect:/login";
	    }
	@DeleteMapping("/users/delete/{id}")
	public void deleteUser(@PathVariable Long id) {
		userEntityRepository.deleteById(id);
		
	}
	
}
