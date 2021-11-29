package com.example.ToDo.Entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {
    public enum TaskStatus{
        Pending,
        Completed
    }
    public enum TaskType{
        Task,
        SubTask
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private Long userName;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private TaskStatus status;
    @Column(name = "type")
    private TaskType type;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "created")
    private LocalDateTime created;

    public Task(String title, String description, TaskStatus status, TaskType type, LocalDateTime dueDate, LocalDateTime created) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.type = type;
        this.dueDate = dueDate;
        this.created = created;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
