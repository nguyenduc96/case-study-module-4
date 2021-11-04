package com.group3.models.user;

import com.group3.models.role.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    private String image;

    private Date vip;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {
    }

    public User(Long id, String username, String password, String email, String phone, String fullName, Date birthday, String image, Date vip, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.birthday = birthday;
        this.image = image;
        this.vip = vip;
        this.roles = roles;
    }
}
