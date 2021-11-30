package com.example.ToDo.Services;


import com.example.ToDo.Entities.SubTask;
import com.example.ToDo.Entities.Task;
import com.example.ToDo.Repositories.SubTaskRepository;
import com.example.ToDo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SubTaskRepository subTaskRepository;

    public ResponseEntity<String> createTask(String userName, Task task) {
        task.setUserName(userName);
        task.setCreated(LocalDateTime.now());
        task.setStatus(Task.TaskStatus.Pending);
        taskRepository.save(task);
        return ResponseEntity.ok("Task Created Successfully");
    }

    public List<Task> getAllTask(String userName, Task.TaskStatus status, String title) {
        if(title!=null){
            return taskRepository.getByUserNameAndTitleContainsIgnoreCase(userName,title);
        }

        if(status==null){
            return taskRepository.getAllByUserName(userName);
        }

        return taskRepository.getAllByUserNameAndStatus(userName,status);

    }

    public List<Task> getAllByTitle(String userName, String title) {
        return taskRepository.getByUserNameAndTitleContainsIgnoreCase(userName,title);
    }

    public ResponseEntity<String> createSubTask(String userName, SubTask subTask, Long taskId) {
        Task task = taskRepository.getById(taskId);
        subTask.setCreated(LocalDateTime.now());
        subTask.setStatus(SubTask.TaskStatus.Pending);
        subTask.setParentTask(task);
        if(subTask.getDueDate()==null){
            subTask.setDueDate(task.getDueDate());
        }
        subTaskRepository.save(subTask);
        return ResponseEntity.ok("Sub Task Created Successfully");
    }
}
