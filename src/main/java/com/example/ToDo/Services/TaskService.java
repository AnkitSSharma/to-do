package com.example.ToDo.Services;


import com.example.ToDo.Entities.SubTask;
import com.example.ToDo.Entities.Task;
import com.example.ToDo.Repositories.SubTaskRepository;
import com.example.ToDo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
public class TaskService {
    public enum DueDateFilter{
        Today,
        Overdue,
        ThisWeek,
        NextWeek
    }
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

    public List<Task> getAllTask(String userName, Task.TaskStatus status, String title, DueDateFilter dueDate) {
        if(status!=null){
            return taskRepository.getAllByUserNameAndStatus(userName,status, Sort.by("dueDate"));
        }
        if(title!=null && dueDate==null){
            return taskRepository.getByUserNameAndTitleContainsIgnoreCase(userName,title,Sort.by("dueDate"));
        }
        if(title!=null){
            if (DueDateFilter.Today.equals(dueDate)) {

                return taskRepository.getAllByDueDateAndUserNameAndTitleContainsIgnoreCase(LocalDateTime.now(), userName,title);
            }
            if (DueDateFilter.Overdue.equals(dueDate)) {

                return taskRepository.getAllByDueDateIsBeforeAndUserNameAndTitleContainsIgnoreCase(LocalDateTime.now(), userName,title, Sort.by("dueDate"));
            }
            if (DueDateFilter.ThisWeek.equals(dueDate)) {
                return taskRepository.getAllByDueDateBetweenAndUserNameAndTitleContainsIgnoreCase(LocalDateTime.now(), LocalDateTime.now().plusDays(6), userName,title, Sort.by("dueDate"));
            }
            if (DueDateFilter.NextWeek.equals(dueDate)) {
                return taskRepository.getAllByDueDateBetweenAndUserNameAndTitleContainsIgnoreCase(LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(13), userName,title, Sort.by("dueDate"));
            }
        }
        else if(dueDate!=null){
            if (DueDateFilter.Today.equals(dueDate)) {

                return taskRepository.getAllByDueDateAndUserName(LocalDateTime.now(), userName);
            }
            if (DueDateFilter.Overdue.equals(dueDate)) {

                return taskRepository.getAllByDueDateIsBeforeAndUserName(LocalDateTime.now(), userName, Sort.by("dueDate"));
            }
            if (DueDateFilter.ThisWeek.equals(dueDate)) {
                return taskRepository.getAllByDueDateBetweenAndUserName(LocalDateTime.now(), LocalDateTime.now().plusDays(6), userName, Sort.by("dueDate"));
            }
            if (DueDateFilter.NextWeek.equals(dueDate)) {
                return taskRepository.getAllByDueDateBetweenAndUserName(LocalDateTime.now().plusDays(7), LocalDateTime.now().plusDays(13), userName, Sort.by("dueDate"));
            }
        }
        return taskRepository.getAllByUserName(userName, Sort.by("dueDate"));


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

    public ResponseEntity<String> markComplete(String userName, Long taskid) {
        Task task = taskRepository.getByIdAndUserName(taskid, userName);
        task.setStatus(Task.TaskStatus.Completed);
        List<SubTask> subtasks = task.getSubtasks();
        for ( SubTask subtask : subtasks){
            subtask.setStatus(SubTask.TaskStatus.Completed);
            subTaskRepository.save(subtask);
        }
        taskRepository.save(task);
        return ResponseEntity.ok("Task marked Completed");
    }
}
