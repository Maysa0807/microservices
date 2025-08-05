package com.ms.user.services;

import com.ms.user.entity.UserEntity;
import com.ms.user.producers.UserProducer;
import com.ms.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer){
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }
    @Transactional
    public UserEntity save(UserEntity userEntity){
        userEntity = userRepository.save(userEntity);
        userProducer.publishMessageEmail(userEntity);
        return userEntity;
    }
}
