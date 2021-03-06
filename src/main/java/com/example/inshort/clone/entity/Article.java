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
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String addedBy;
    private Timestamp timestamp;

    @ManyToMany
            @JoinTable(
                    name = "article_category",
                    joinColumns = @JoinColumn(name = "articleId"),
                    inverseJoinColumns = @JoinColumn(name = "categoryId")
            )

    List<Category> categories;

}
