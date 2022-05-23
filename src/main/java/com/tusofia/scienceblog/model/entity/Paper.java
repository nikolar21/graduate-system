package com.tusofia.scienceblog.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Paper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
