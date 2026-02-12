package com.prady.blogWeb.controller;


import com.prady.blogWeb.dto.request.AuthRequest;
import com.prady.blogWeb.dto.request.CreateUser;
import com.prady.blogWeb.dto.response.AuthResponse;
import com.prady.blogWeb.dto.response.UserResponse;
import com.prady.blogWeb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService= userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody CreateUser createUser){
        userService.register(createUser);
        Map<String,String> response = Map.of("message","User Registered Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){

        return ResponseEntity.ok(userService.login(authRequest));
    }

}
