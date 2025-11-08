package com.todo.todoapp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Title is required ")
    @Size(min = 3,max = 90,message = "Title must be 3-50 Chars")
    private String title;
    private boolean completed;
    // empty constactor is important For JPA "java persistence API "
    public Task (){}
    // Constructor
    public Task(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user ;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
