package com.group3.models.report;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String content;
}
