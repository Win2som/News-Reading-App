package com.example.inshort.clone.service;

import com.example.inshort.clone.entity.Article;
import com.example.inshort.clone.model.ArticleDto;
import com.example.inshort.clone.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


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

    public List<Article> getAllArticles(Integer pageNo, Integer size) {
        if(Objects.isNull(pageNo) && Objects.isNull(size)){
            return articleRepository.findAll();
        }

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by("timestamp").descending());
        Page<Article> page = articleRepository.findAll(pageable);

        return page.stream().collect(Collectors.toList());
    }

    public Article getAnArticle(Long id){
        return articleRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("article with id %s not found",id)));
    }

    public void updateArticle(Long id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("article with id %s not found",id)));

        article.setTitle(articleDto.getTitle());
        article.setDescription(articleDto.getDescription());
        article.setAddedBy(articleDto.getAddedBy());

        articleRepository.save(article);
    }

    public void updateArticle(Long id, @RequestBody Map<String, Object> requestDto) {
        Article article = articleRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("article with id %s not found",id)));

        //updates only fields found in the Dto
        requestDto.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Article.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, article, v);
        });

        articleRepository.save(article);

    }


}
