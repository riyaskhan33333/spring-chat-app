package com.livechat.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livechat.chat.entity.MessageEntity;

public interface MessageEntityRepository extends JpaRepository<MessageEntity,Long>{
    List<MessageEntity> findMessageEntitiesByTopic(String topic);
}
