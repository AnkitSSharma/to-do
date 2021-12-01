package com.example.ToDo.Repositories;

import com.example.ToDo.Entities.SubTask;
import com.example.ToDo.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask,Long> {
}
