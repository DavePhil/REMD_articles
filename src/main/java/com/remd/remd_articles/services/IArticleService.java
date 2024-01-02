package com.remd.remd_articles.services;

import com.remd.remd_articles.models.Article;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IArticleService {
    Article create(Article article);
    Article update(Article article);
    List<Article> getAll();
    ResponseEntity<?> delete(Long id);
}
