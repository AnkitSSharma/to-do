package com.example.ToDo.Services;

import com.example.ToDo.Entities.User;
import com.example.ToDo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserService {
    @Autowired

    private UserRepository userRepository;
    public ResponseEntity<String> createUser(User user) {

        if(user.getName()==null||user.getUserName()==null||user.getPassword()==null||user.getEmail()==null){
            return new ResponseEntity<>(
                    "Incorrect Inputs",
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUserName(user.getUserName())){
            return new ResponseEntity<>(
                    "User Name Already Taken",
                    HttpStatus.BAD_REQUEST);
        }
        String unHashedPassword = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(unHashedPassword);
        user.setPassword(hashedPassword);
        user.setCreated(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok("User Created Succesfully");
    }

    public boolean isValid(String userName, String password) {
        if(!userRepository.existsByUserName(userName)){
            return false;
        }

        User user = userRepository.getByUserName(userName);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password,user.getPassword());
    }
}
