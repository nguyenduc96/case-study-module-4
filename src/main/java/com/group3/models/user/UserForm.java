package com.group3.models.user;

import com.group3.models.role.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

@Data
public class UserForm {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String fullName;

    private String birthday;

    private MultipartFile image;

    private Date vip;

    private List<Role> roles;
}
