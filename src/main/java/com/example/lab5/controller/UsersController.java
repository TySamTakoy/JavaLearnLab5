package com.example.lab5.controller;


import com.example.lab5.service.SecurityUserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final SecurityUserDetailsService securityUserDetailsService;

    public UsersController(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    @PutMapping
    public void createUser(@RequestHeader String username, @RequestHeader String password) {
        securityUserDetailsService.createUser(username, password);
    }

}