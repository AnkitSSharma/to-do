package com.example.ToDo.Controllers;


import com.example.ToDo.Entities.Task;
import com.example.ToDo.Services.TaskService;
import com.example.ToDo.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TaskService taskService;
    @PostMapping("/task/create")
    ResponseEntity<String> createTask(@RequestBody Task task,@RequestHeader(value="Authorization") String jwt){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        return taskService.createTask(userName,task);
    }
    @GetMapping("/task/getall")
    List<Task> getAllTask(@RequestHeader(value="Authorization") String jwt,@RequestParam(value="status", required = false) Task.TaskStatus status,@RequestParam(value = "title",required = false) String title){
        jwt = jwt.substring(7);
        String userName = jwtUtil.extractUsername(jwt);
        return taskService.getAllTask(userName,status,title);
    }

}
