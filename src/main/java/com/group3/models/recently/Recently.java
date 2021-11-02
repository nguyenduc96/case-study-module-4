package com.group3.models.recently;

import com.group3.models.music.Music;
import com.group3.models.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Recently {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Music music;
}
