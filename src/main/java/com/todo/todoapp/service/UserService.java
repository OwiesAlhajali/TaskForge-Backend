package com.todo.todoapp.service;


import com.todo.todoapp.exception.UserNotFoundException;
import com.todo.todoapp.model.User;
import com.todo.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repo ;
    public User addUser (User newUser){
        return repo.save(newUser) ;
    }
    public List<User> getAllUsers(){
        return repo.findAll();
    }
    public User updateUser (int id,User updateUser ){
        User user = repo.findById(id).orElseThrow(()->new UserNotFoundException("User With Id"+id+"Not Found.."));
        if(updateUser.getName()!=null){
            user.setName(updateUser.getName());
        }

        return repo.save(user);
    }
    public boolean deleteUser (int id ){
        if(!repo.existsById(id))
            return false;
        else {
            repo.deleteById(id);
            return true ;
        }
    }
}
