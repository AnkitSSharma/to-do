package com.example.ToDo.Entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="tasks")
public class Task {

    public enum TaskStatus{
        Pending,
        Completed
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
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
    @Column(name = "reminderBefore")
    private Integer reminderBefore;

    @JsonManagedReference
    @OneToMany(
            mappedBy = "parentTask",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch =FetchType.EAGER
    )
    private List<SubTask> subtasks = new ArrayList<>();

    public Task() {

    }

    public Task(String userName, String title, String description, TaskStatus status, LocalDateTime dueDate, LocalDateTime created, List<SubTask> subTasks) {
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.created = created;
    }

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTask> subtasks) {
        this.subtasks = subtasks;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getReminderBefore() {
        return reminderBefore;
    }

    public void setReminderBefore(Integer reminderBefore) {
        this.reminderBefore = reminderBefore;
    }
}
