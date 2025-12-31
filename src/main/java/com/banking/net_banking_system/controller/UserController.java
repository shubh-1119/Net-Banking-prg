package com.banking.net_banking_system.controller;

import com.banking.net_banking_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> payload){
        String email = payload.get("email");
        String password = payload.get("password");

        return authService.login(email,password);
    }

}
