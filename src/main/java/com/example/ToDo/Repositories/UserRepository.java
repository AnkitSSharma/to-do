package com.example.ToDo.Repositories;

import com.example.ToDo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User getByUserName(String userName);

    Optional<Object> findByUserName(String userName);
}
