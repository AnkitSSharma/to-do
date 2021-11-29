package com.example.ToDo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users",uniqueConstraints =@UniqueConstraint(name = "user_name",columnNames={"name","email"}))
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name = "created")
    private LocalDateTime created;

    public UserDetail(String name, String userName, String password, LocalDateTime created) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.created = created;
    }

    public UserDetail() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
