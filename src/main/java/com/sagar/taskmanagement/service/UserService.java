package com.sagar.taskmanagement.service;

import com.sagar.taskmanagement.model.Users;
import com.sagar.taskmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;



    public UserService(UserRepo userRepo, JwtService jwtService){
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    }

    public Users register(Users user){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }
}
