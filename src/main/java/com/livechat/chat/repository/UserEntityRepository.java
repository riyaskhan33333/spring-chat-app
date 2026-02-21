package com.livechat.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livechat.chat.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long>{
   Optional<UserEntity> findUserEntityByUserName(String userName);
   boolean existsByUserName(String userName);
}
