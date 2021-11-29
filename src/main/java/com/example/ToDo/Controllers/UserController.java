package com.example.ToDo.Controllers;


import com.example.ToDo.Entities.User;
import com.example.ToDo.Services.UserService;
import com.example.ToDo.Utils.JwtUtil;
import com.example.ToDo.Utils.MyUserDetailImpl;
import com.example.ToDo.Utils.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyUserDetailService myuserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/user/login")
    public ResponseEntity<String> isValid(@RequestBody User user){
        if(userService.isValid(user.getUserName(),user.getPassword())) {
            UserDetails userDetails = myuserDetailService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken((MyUserDetailImpl) userDetails);
            return ResponseEntity.ok(jwt);
        }
        else
            return ResponseEntity.ok("UnAuthenticated User");
    }

    @PostMapping("/user/signup")
    ResponseEntity<String> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

}
