package com.example.ToDo.Repositories;

import com.example.ToDo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User getByUserName(String name);

    Optional<Object> findByUserName(String userName);
}
