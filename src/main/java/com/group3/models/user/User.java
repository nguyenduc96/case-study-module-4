package com.group3.models.user;

import com.group3.models.role.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String username;

    @Column(columnDefinition = "text", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "varchar(12)", nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String fullName;

    private Date birthday;

    private Date vip;
}
