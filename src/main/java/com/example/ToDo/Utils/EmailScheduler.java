package com.example.ToDo.Utils;

import com.example.ToDo.Entities.Task;
import com.example.ToDo.Entities.User;
import com.example.ToDo.Repositories.TaskRepository;
import com.example.ToDo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

public class EmailScheduler {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
     TaskRepository taskRepository;
    @Autowired
     UserRepository userRepository;
    @Scheduled(cron = "0 0 * * * *")
    void triggerMail(){
        List<Task> tasks = taskRepository.getAllByStatus(Task.TaskStatus.Pending);
        for(Task task : tasks){
            if(task.getReminderBefore() != null) {
                LocalDateTime dueDate = task.getDueDate();
                LocalDateTime now = LocalDateTime.now();
                Duration duration = Duration.between(dueDate,now);
                if (duration.toHours()==task.getReminderBefore()) {
                    String userName = task.getUserName();
                    User user = userRepository.getByUserName(userName);
                    emailSenderService.sendSimpleEmail(user.getEmail(), "Pending Task with Title" + task.getTitle(), "This is a Reminder");
                }
            }
        }
    }
}