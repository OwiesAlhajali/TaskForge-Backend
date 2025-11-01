package com.todo.todoapp.service;

import com.todo.todoapp.exception.TaskNotFoundException;
import com.todo.todoapp.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class TaskService {
    private List<Task> tasks = new ArrayList<>(List.of

            (new Task(1,"spring",false) ,
            new Task (2,"java",true))
    );
    public List<Task> getAlltasks(){
        return tasks ;
    }
    public Task addTask (Task newTask){
        tasks.add(newTask);
        return newTask ;
    }
    public Task UpdateTask(int id, Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                if (updatedTask.getTitle() != null)
                    task.setTitle(updatedTask.getTitle());
                task.setCompleted(updatedTask.isCompleted());
                return task;
            }
        }
        throw new TaskNotFoundException("Task with id" + id + "Not found..") ;
    }
    public boolean deleteTask (int id){
        return tasks.removeIf(task->task.getId()==id);
    }
}
