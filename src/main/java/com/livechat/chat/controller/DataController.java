package com.livechat.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.livechat.chat.entity.MessageEntity;
import com.livechat.chat.repository.MessageEntityRepository;

@RestController
@CrossOrigin("*")
public class DataController {
	@Autowired
	private MessageEntityRepository messageRepository;
	@GetMapping("/api/messages/{topic}")
	public List<MessageEntity> retrieveMessages(@PathVariable String topic)
	{
		return messageRepository.findMessageEntitiesByTopic(topic);
	}

}
