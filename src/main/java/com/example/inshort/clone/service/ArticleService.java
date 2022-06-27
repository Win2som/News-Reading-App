package com.example.inshort.clone.service;

import com.example.inshort.clone.entity.Article;
import com.example.inshort.clone.model.ArticleDto;
import com.example.inshort.clone.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticles(){

        return articleRepository.findAll();
    }

    public String createArticle(ArticleDto articleDto){

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .description(articleDto.getDescription())
                .addedBy(articleDto.getAddedBy())
                .timestamp(new Timestamp(new Date().getTime()))
                .build();

        articleRepository.save(article);

        return "";
    }

    public Article getAnArticle(Long id){
        return articleRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("article with id %s not found",id)));
    }
}
