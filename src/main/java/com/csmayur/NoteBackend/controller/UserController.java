package com.csmayur.NoteBackend.controller;

import com.csmayur.NoteBackend.model.User;
import com.csmayur.NoteBackend.model.UserLogin;
import com.csmayur.NoteBackend.repository.UserRepo;
import com.csmayur.NoteBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        return userService.registeruser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLogin userLogin){
        return userService.login(userLogin);
    }
}
