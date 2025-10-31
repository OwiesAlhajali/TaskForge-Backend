package com.todo.todoapp.controller;

import com.todo.todoapp.model.Task;
import com.todo.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TaskController {
    @Autowired
    private TaskService taskService ;


    // Show me all the Tasks
    @GetMapping("/tasks")
    public List<Task> GetAllTasks(){
        return taskService.getAlltasks();
    }
    // Add New task Using post req
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task newTask){
        return taskService.addTask(newTask);
    }
    //Update the Info of the Task Using Put , dont forget that requestbody take the
    //Info from the body of JSON File
    @PutMapping("/tasks/{id}")
    public Task UpdatTask(@PathVariable int id ,@RequestBody Task updateTask ){
        return taskService.UpdateTask( id , updateTask) ;
    }
    @DeleteMapping("/tasks/{id}")
    public String deleteTask (@PathVariable int id){
        boolean removed = taskService.deleteTask(id);
        if (removed)
            return "task with id " + id + "deleted successfully..";
        else
            return "task with id " + id + "Not Found .. " ;
    }
}
