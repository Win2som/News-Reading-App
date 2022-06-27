package com.example.inshort.clone.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Timestamp timestamp;

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;
}
