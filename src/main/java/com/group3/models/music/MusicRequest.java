package com.group3.models.music;

import com.group3.models.category.Category;
import com.group3.models.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class MusicRequest {

    private Long id;

    private String name;

    private Date created;

    private String description;

    private Long downloads = 0L;

    private MultipartFile image;

    private MultipartFile song;

    private boolean vip = false;

    private User user;

    private Category category;
}
