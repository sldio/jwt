package com.jwt.service;

import com.jwt.entity.User;
import com.jwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public User makeNewUser(Map<String, String[]> map)
    {
        User user = new User();

        user.setPassword(map.get("password")[0]);
        user.setName(map.get("name")[0]);
        user.setRole(map.get("role")[0]);

        saveUser(user);

        return user;
    }

    public User getUserById(String id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){

        userRepository.save(user);

        return user;
    }
}
