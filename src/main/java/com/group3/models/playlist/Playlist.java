package com.group3.models.playlist;

import com.group3.models.music.Music;
import com.group3.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<User> user;

    @ManyToMany
    private List<Music> music;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private  String name;
}
