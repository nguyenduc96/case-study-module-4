package com.group3.controller;

import com.group3.models.JwtResponse;
import com.group3.models.role.Role;
import com.group3.models.user.User;
import com.group3.models.user.UserForm;
import com.group3.services.JwtService;
import com.group3.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }

//    @PostMapping("/register")
//    public ResponseEntity<?> doRegister(UserForm userForm) throws ParseException {
//
//        MultipartFile multipartFile = userForm.getImage();
//        String fileName = multipartFile.getOriginalFilename() ;
//        String date = String.valueOf(new Date().getTime());
//        try {
//            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload+ "\\image\\"+ date + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role(2L,"ROLE_USER"));
//        User user = new User();
//        user.setId(userForm.getId());
//        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userForm.getBirthday()));
//        user.setEmail(userForm.getEmail());
//        user.setFullName(userForm.getFullName());
//        user.setPhone(userForm.getPhone());
//        user.setUsername(userForm.getUsername());
//        user.setPassword(userForm.getPassword());
//        user.setImage(date+fileName);
//        user.setRoles(roles);
//        userService.save(user);
//        return new ResponseEntity<>(user,HttpStatus.CREATED);
//    }

}
