package com.todo.todoapp.controller;
import com.todo.todoapp.model.User;
import com.todo.todoapp.repository.UserRepository;
import com.todo.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService ;
    // Add new User ..
    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User newUser){
        if(newUser.getName()==null || newUser.getName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
        }
        User savedUser= userService.addUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
   @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User>users= userService.getAllUsers() ;
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.ok(users);
        }
   }
   @PutMapping("/{id}")
    public ResponseEntity<User>updateUser (@PathVariable int id ,@RequestBody User updateUser ){
        User user = userService.updateUser(id,updateUser);
        if (user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.ok(user);
        }
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<User>deleteUser(@PathVariable int id ) {
        boolean removed = userService.deleteUser(id);
        if (removed){
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
   }
}
