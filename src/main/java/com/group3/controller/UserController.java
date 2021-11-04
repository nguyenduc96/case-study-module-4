package com.group3.controller;

import com.group3.models.role.Role;
import com.group3.models.user.User;
import com.group3.models.user.UserForm;
import com.group3.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    public static final String IMAGE_PATH = "image/";
    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    private IUserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping ("/getAll")
    public ResponseEntity<?> adminPage(@PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false) String q) {
        Page<User> users;
        if(q.equals("") || q == null) {
            users = userService.findAll(pageable);
        } else {
            users = userService.findByUsernameContaining(q);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserForm userForm) throws ParseException {
        MultipartFile multipartFile = null;
        String fileName = "";
        if(userForm.getImage() != null) {
            multipartFile = userForm.getImage();
            fileName = multipartFile.getOriginalFilename() ;
        }
        String date = String.valueOf(new Date().getTime());
        copyImageToFolder(multipartFile, fileName, date);
        List<Role> roles = new ArrayList<>();
        if(userForm.getRoles() == null) {
            roles.add(new Role(2L,"ROLE_USER"));
        }else {
            roles = userForm.getRoles();
        }
        User user = new User();
        user.setId(userForm.getId());
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userForm.getBirthday()));
        user.setEmail(userForm.getEmail());
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        if(!fileName.equals("")) {
            user.setImage(date+fileName);
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@RequestBody UserForm userForm, @PathVariable Long id ) {
        Optional<User> user1 = userService.findById(id);
        if (!user1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deleteOldImage(user1);
        MultipartFile multipartFile = null;
        String fileName = "";
        if(userForm.getImage() != null) {
            multipartFile = userForm.getImage();
            fileName = multipartFile.getOriginalFilename() ;
        }

        String date = String.valueOf(new Date().getTime());
        copyImageToFolder(multipartFile, fileName, date);
        List<Role> roles = new ArrayList<>();
        if(userForm.getRoles() == null) {
            roles.add(new Role(2L, "ROLE_USER"));
        }else {
            roles = userForm.getRoles();
        }
        User user = new User();
        user.setId(id);
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userForm.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(userForm.getEmail());
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        if(!fileName.equals("")) {
            user.setImage(date+fileName);
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id) {
        Optional<User> user1 = userService.findById(id);
        if (!user1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        deleteOldImage(user1);
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void copyImageToFolder(MultipartFile multipartFile, String fileName, String date) {
        if(multipartFile != null) {
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + IMAGE_PATH + date + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteOldImage(Optional<User> user1) {
        if(user1.get().getImage()!=null) {
            File fileImage = new File(fileUpload + IMAGE_PATH + user1.get().getImage());
            fileImage.delete();
        }
    }
}