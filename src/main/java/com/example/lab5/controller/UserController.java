package com.example.lab5.controller;


import com.example.lab5.service.SecurityUserDetailsService;
import com.example.lab5.service.ShopService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SecurityUserDetailsService securityUserDetailsService;

    public UserController(SecurityUserDetailsService securityUserDetailsService) {
        this.securityUserDetailsService = securityUserDetailsService;
    }

    @PutMapping
    public void createUser(@RequestHeader String username, @RequestHeader String password) {
        securityUserDetailsService.createUser(username, password);
    }

}