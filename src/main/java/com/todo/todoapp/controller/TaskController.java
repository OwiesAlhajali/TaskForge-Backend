package com.todo.todoapp.controller;

import com.todo.todoapp.model.Task;
import com.todo.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TaskController {
    @Autowired
    private TaskService taskService ;


    // Show me all the Tasks
    @GetMapping("/tasks")
    public ResponseEntity <List<Task>> GetAllTasks(){

        List<Task>tasks=taskService.getAlltasks();
        if(tasks.isEmpty()){
            //If There is no tasks what to Do ?? No content 204
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            // if there is data ok 200
            return ResponseEntity.ok(tasks) ;
        }
    }
    // Add New task Using post req

    @PostMapping("/tasks")
    public ResponseEntity <Task> addTask(@RequestBody Task newTask)
    {
        if(newTask.getTitle()==null || newTask.getTitle().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Task savedTask=taskService.addTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask) ;
    }
    //Update the Info of the Task Using Put , dont forget that requestbody take the
    //Info from the body of JSON File
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> UpdatTask(@PathVariable int id ,@RequestBody Task updateTask ){
        Task task =taskService.UpdateTask( id , updateTask) ;
        if (task==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(task); // Mind here 200 Mean OK
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask (@PathVariable int id){
        boolean removed = taskService.deleteTask(id);
        if (removed)
            return ResponseEntity.noContent().build(); // Here 204 mean no Content
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;// 404 Not Fucking Found ..
    }
}
