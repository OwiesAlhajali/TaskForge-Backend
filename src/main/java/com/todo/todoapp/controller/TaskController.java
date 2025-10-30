package com.todo.todoapp.controller;

import com.todo.todoapp.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1,"spring",false) ,
            new Task (2,"java",true)
    ));
    // Show me all the Tasks
    @GetMapping("/tasks")
    public List<Task> GetAllTasks(){
        return tasks ;
    }
    // Add New task Using post req
    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task newTask){
        tasks.add(newTask);
        return newTask ;
    }
    //Update the Info of the Task Using Put , dont forget that requestbody take the
    //Info from the body of JSON File
    @PutMapping("/tasks/{id}")
    public Task UpdatTask(@PathVariable int id ,@RequestBody Task UpdatedTask ){
        for (Task task : tasks ){
            if (task.getId()==id){
                if(UpdatedTask.getTitle()!=null){
                    task.setTitle(UpdatedTask.getTitle());
                }
                task.setCompleted(UpdatedTask.isCompleted());
                return task ;
            }

        }
        return null ;
    }
    @DeleteMapping("/tasks/{id}")
    public String deleteTask (@PathVariable int id) {
        boolean remove = tasks.removeIf(task -> task.getId() == id);
        if (remove)
            return "Task with id " + id + "Deleted successfully..";
        else
            return "Task with id " + id + "Not found..";

    }

}
