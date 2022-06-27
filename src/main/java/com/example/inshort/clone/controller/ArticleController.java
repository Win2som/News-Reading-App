package com.example.inshort.clone.controller;

import com.example.inshort.clone.entity.Article;
import com.example.inshort.clone.model.ArticleDto;
import com.example.inshort.clone.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

   @Autowired
   ArticleService articleService;

    @GetMapping("/article")
    public List<Article> getAllArticles(){

        return articleService.getAllArticles();
    }

    @PostMapping("/article")
    public ResponseEntity<String> createArticle(@RequestBody ArticleDto articleDto){
        articleService.createArticle(articleDto);
        return new ResponseEntity<>("New article created", HttpStatus.CREATED);
    }

    @GetMapping("/article/{id}")
    public Article getAnArticle(@PathVariable("id") Long id){
        return articleService.getAnArticle(id);
    }
}
