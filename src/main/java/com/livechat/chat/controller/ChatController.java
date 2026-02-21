package com.livechat.chat.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.livechat.chat.entity.MessageEntity;
import com.livechat.chat.repository.MessageEntityRepository;

@Controller
@CrossOrigin("*")
public class ChatController{ //STOMP message handler
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private MessageEntityRepository messageRepository;
	
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	@MessageMapping("/sendtoanime")// listens to /app/sendtoanime/
	public void sendToAnime(@Payload MessageEntity message,Principal principal) {
		message.setSenderId(principal.getName());
		messageRepository.save(message);
		
		messagingTemplate.convertAndSend("/topic/anime",message);
		
	}
	
	
	@MessageMapping("/sendtofood")
	public void sendToFood(@Payload MessageEntity message,Principal principal) {
		message.setSenderId(principal.getName());
		messageRepository.save(message);
		
		messagingTemplate.convertAndSend("/topic/food",message);
	}
	
	@MessageMapping("/sendtotech")
	public void sendToTech(@Payload MessageEntity message,Principal principal) {
		message.setSenderId(principal.getName());
		messageRepository.save(message);
		
		messagingTemplate.convertAndSend("/topic/tech",message);
	}
	
	@MessageMapping("/sendtofunny")
	public void sendToFunny(@Payload MessageEntity message,Principal principal) {
		message.setSenderId(principal.getName());
		messageRepository.save(message);
		
		messagingTemplate.convertAndSend("/topic/funny",message);
	}
}