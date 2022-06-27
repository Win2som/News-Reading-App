package com.example.inshort.clone.repository;

import com.example.inshort.clone.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
