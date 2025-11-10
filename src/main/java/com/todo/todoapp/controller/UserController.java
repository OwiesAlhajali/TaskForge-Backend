package com.todo.todoapp.controller;

import com.todo.todoapp.model.User;
import com.todo.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    // Add new User ..
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        if(newUser.getName()==null || newUser.getName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
        }
        User savedUser=userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
   /* @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users) ;
        .
        .
        .
        We Need also Put & Delete ..
    }*/
}
