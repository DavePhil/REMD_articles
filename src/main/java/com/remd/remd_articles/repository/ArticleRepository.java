package com.remd.remd_articles.repository;

import com.remd.remd_articles.models.Article;
import com.remd.remd_articles.models.ArticleState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Modifying
    @Transactional
    @Query("update Article article set article.state = ?1 where article.id = ?2")
    int modifyState(ArticleState articleState, Long id);
}
