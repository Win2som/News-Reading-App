package com.example.inshort.clone.controller;

import com.example.inshort.clone.entity.Article;
import com.example.inshort.clone.model.ArticleDto;
import com.example.inshort.clone.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

   @Autowired
   ArticleService articleService;


    @PostMapping("/article")
    public ResponseEntity<String> createArticle(@RequestBody ArticleDto articleDto){
        articleService.createArticle(articleDto);
        return new ResponseEntity<>("New article created", HttpStatus.CREATED);
    }

    @GetMapping("/article")
    public List<Article> getAllArticles(@RequestParam(name="pageNo", required = false)Integer pageNo, @RequestParam(name="size", required = false)Integer size){

        return articleService.getAllArticles(pageNo, size);
    }

    @GetMapping("/article/{id}")
    public Article getAnArticle(@PathVariable("id") Long id){
        return articleService.getAnArticle(id);
    }

    @PutMapping("/article/{id}")
    public String updateArticle(@PathVariable("id") Long id, @RequestBody ArticleDto articleDto){
        articleService.updateArticle(id, articleDto);

        return "Article has been updated";
    }

    @PatchMapping("/article/{id}")
    public String updateArticle(@PathVariable("id") Long id, @RequestBody Map<String, Object> requestDto){
        articleService.updateArticle(id, requestDto);

        return "Article has been updated";
    }


}
