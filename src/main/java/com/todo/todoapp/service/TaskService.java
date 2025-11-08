package com.todo.todoapp.service;

import com.todo.todoapp.exception.TaskNotFoundException;
import com.todo.todoapp.model.Task;
import com.todo.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskService {
@Autowired
private TaskRepository repo ;
    public List<Task> getAlltasks(){
        return repo.findAll() ;
    }
    public Task addTask (Task newTask){
        return repo.save(newTask);
    }
    public Task UpdateTask(int id, Task updatedTask) {
        Task task=repo.findById(id)
                .orElseThrow(()->new TaskNotFoundException("task with this id" +id+ "Not Found.."));
        if(updatedTask.getTitle()!=null){
            task.setTitle(updatedTask.getTitle());
        }
        task.setCompleted(updatedTask.isCompleted());
        return repo.save(task);
    }
    public boolean deleteTask (int id){
        if(!repo.existsById(id))
            return false;
        repo.deleteById(id);
        return true ;
    }
}
