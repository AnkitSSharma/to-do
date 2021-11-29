package com.example.ToDo.Utils;

import com.example.ToDo.Entities.User;
import com.example.ToDo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetailImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username : " + userName));
        return new MyUserDetailImpl(user);
    }
}
