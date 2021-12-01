package com.example.ToDo.Controllers;


import com.example.ToDo.Entities.SubTask;
import com.example.ToDo.Entities.Task;
import com.example.ToDo.Repositories.TaskRepository;
import com.example.ToDo.Services.TaskService;
import com.example.ToDo.Utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository repo;
    @PostMapping("/task/create")
    ResponseEntity<String> createTask(@RequestBody Task task,@RequestHeader(value="Authorization") String jwt){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        return taskService.createTask(userName,task);
    }

    @PostMapping("/subtask/create")
    ResponseEntity<String> createSubTask(@RequestBody SubTask subTask, @RequestHeader(value="Authorization") String jwt, @RequestParam(value="taskid") Long taskId){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        return taskService.createSubTask(userName,subTask,taskId);
    }
    @GetMapping("/task/getall")
    List<Task> getAllTask(@RequestHeader(value="Authorization") String jwt, @RequestParam(value="status", required = false) Task.TaskStatus status, @RequestParam(value = "title",required = false) String title,@RequestParam(value = "duedate",required = false) TaskService.DueDateFilter duedate){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        System.out.println(duedate);
        return taskService.getAllTask(userName,status,title, duedate);
    }

    @GetMapping("/task/markcomplete")
    ResponseEntity<String> markComplete(@RequestHeader(value="Authorization") String jwt,@RequestParam(value = "taskid") Long taskid){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        return taskService.markComplete(userName,taskid);
    }




}
