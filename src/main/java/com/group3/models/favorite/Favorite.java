package com.group3.models.favorite;

import com.group3.models.music.Music;
import com.group3.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<User> user;

    @ManyToMany
    private List<Music> music;
}
