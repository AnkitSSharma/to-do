package com.example.ToDo.Repositories;

import com.example.ToDo.Entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> getAllByUserNameAndStatus(String userName, Task.TaskStatus taskStatus, Sort sort);

    List<Task> getByUserNameAndTitleContainsIgnoreCase(String userName,String title, Sort sort);

    List<Task> getAllByUserName(String userName,Sort sort);

    boolean findByUserName(String userName);

    Task getByIdAndUserName(Long taskid, String userName);

    List<Task> getAllByDueDateAndUserName(LocalDateTime dueDate, String userName);
    List<Task> getAllByDueDateIsBeforeAndUserName(LocalDateTime dueDate, String  userName, Sort sort);
    List<Task> getAllByDueDateBetweenAndUserName(LocalDateTime start, LocalDateTime end, String userName, Sort sort);

    List<Task> getAllByDueDateBetweenAndUserNameAndTitleContainsIgnoreCase(LocalDateTime start, LocalDateTime end, String userName, String title, Sort sort);

    List<Task> getAllByDueDateIsBeforeAndUserNameAndTitleContainsIgnoreCase(LocalDateTime dueDate, String userName, String title, Sort sort);

    List<Task> getAllByDueDateAndUserNameAndTitleContainsIgnoreCase(LocalDateTime dueDate, String userName, String title);

    List<Task> getAllByStatus(Task.TaskStatus status);
}
