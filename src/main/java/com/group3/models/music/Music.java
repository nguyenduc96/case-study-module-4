package com.group3.models.music;

import com.group3.models.category.Category;
import com.group3.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String name;

    private Date created;

    @Column(columnDefinition = "text")
    private String description;

    private Long downloads = 0L;

    private String image;

    private String song;

    private boolean vip = false;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;
}
