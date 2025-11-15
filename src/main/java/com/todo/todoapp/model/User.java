package com.todo.todoapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Task> tasks ;
    public User (){}
    public User(int id , String name ){
        this.id=id ;
        this.name =name ;
    }
    public int getId(){return id ;}
    public void setId(int id){this.id=id ;}
    public String getName(){return name ;}
    public void setName(String name){this.name=name;}
}