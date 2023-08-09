package com.sai.service;

import com.sai.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
	
    public String add(List<User> users) {
        if(!users.isEmpty()){
            for(User user : users){
                kafkaTemplate.send("user-messages",user);
            }
        }
        return "User Record Added to Kafka Queue";
    }

    @KafkaListener(topics = "user-messages",groupId = "userGroup")
    public User listener(User user){
    	System.out.println(user);
        return user;
    }
}