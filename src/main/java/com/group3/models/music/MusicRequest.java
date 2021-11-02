package com.group3.models.music;

import com.group3.models.user.User;
import lombok.Data;

import java.util.Date;

@Data
public class MusicRequest {

    private Long id;

    private String name;

    private Date created;

    private String description;

    private Long downloads = 0L;

    private String image;

    private String song;

    private boolean vip = false;

    private User user;
}
