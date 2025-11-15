package com.todo.todoapp.service;

import com.todo.todoapp.exception.TaskNotFoundException;
import com.todo.todoapp.exception.UserNotFoundException;
import com.todo.todoapp.model.Task;
import com.todo.todoapp.model.User;
import com.todo.todoapp.repository.TaskRepository;
import com.todo.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskService {
@Autowired
private TaskRepository taskRepo  ;
@Autowired
private UserRepository userRepo ;
public List<Task> getTasksForUser(int userId){
    if(!taskRepo.existsById(userId)){
        throw new UserNotFoundException("User With Id "+userId+"Not Found ");
    }
    else{
        return taskRepo.findByUserId(userId);
    }
}
public Task addTaskForUser (int userId,Task newTask){
    User user = userRepo.findById(userId)
            .orElseThrow(()->new UserNotFoundException("User with id :"+userId+"Not Found"));
    if(newTask.getTitle()==null || newTask.getTitle().isBlank()){
        throw new IllegalArgumentException("Task with Title Connet be Empty ..");
    }
    newTask.setUser(user);
    return taskRepo.save(newTask) ;
}
    public List<Task> getAlltasks(){
        return taskRepo.findAll() ;
    }
    public Task addTask (Task newTask){
        return taskRepo.save(newTask);
    }
    public Task UpdateTask(int id, Task updatedTask) {
        Task task=taskRepo.findById(id)
                .orElseThrow(()->new TaskNotFoundException("task with this id" +id+ "Not Found.."));
        if(updatedTask.getTitle()!=null){
            task.setTitle(updatedTask.getTitle());
        }
        task.setCompleted(updatedTask.isCompleted());
        return taskRepo.save(task);
    }
    public boolean deleteTask (int id){
        if(!taskRepo.existsById(id))
            return false;
        taskRepo.deleteById(id);
        return true ;
    }
}
