package com.example.ToDo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="sub_task")
public class SubTask {
    public enum TaskStatus{
        Pending,
        Completed
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private TaskStatus status;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @Column(name = "created")
    private LocalDateTime created;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name="parent_task_id")
    private Task parentTask;



    public SubTask() {
    }

    public SubTask(String title, String description, TaskStatus status, LocalDateTime dueDate, LocalDateTime created, Task parentTask) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.created = created;
        this.parentTask = parentTask;
    }

    public SubTask(String title, String description, Task parentTask) {
        this.title = title;
        this.description = description;
        this.parentTask = parentTask;
    }

    public Long getId() {
        return id;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
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
