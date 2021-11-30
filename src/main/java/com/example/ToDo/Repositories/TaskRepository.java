package com.example.ToDo.Repositories;

import com.example.ToDo.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> getAllByUserNameAndStatus(String userName, Task.TaskStatus taskStatus);

    List<Task> getByUserNameAndTitleContainsIgnoreCase(String userName,String title);

    List<Task> getAllByUserName(String userName);
}
