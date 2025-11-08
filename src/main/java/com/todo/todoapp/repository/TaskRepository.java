package com.todo.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todo.todoapp.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByTitle(String title) ;
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findByTitleContaining(String Keyword);
    List<Task> findByTitleStartingWith(String Prefix) ;
}
// the first one is : select from Task where Title =? ;
// the secound one is : select from Task where Completed = True ;
// the third one is : select from Task where completed = False ;
// findByTitleContaining : SELECT * FROM tasks WHERE title LIKE '%java%';