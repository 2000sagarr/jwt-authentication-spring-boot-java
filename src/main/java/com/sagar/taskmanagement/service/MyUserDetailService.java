package com.sagar.taskmanagement.service;

import com.sagar.taskmanagement.model.Users;
import com.sagar.taskmanagement.model.UserPrincipal;
import com.sagar.taskmanagement.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    public MyUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if(users == null){
            throw new UsernameNotFoundException("User not found. Username: "+username);
        }
        return new UserPrincipal(users);
    }
}
