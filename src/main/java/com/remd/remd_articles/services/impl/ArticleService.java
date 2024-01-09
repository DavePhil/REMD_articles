package com.remd.remd_articles.services.impl;

import com.remd.remd_articles.models.Article;
import com.remd.remd_articles.models.ArticleState;
import com.remd.remd_articles.repository.ArticleRepository;
import com.remd.remd_articles.services.IArticleService;
import com.remd.remd_articles.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article create(Article article) {
        return articleRepository.save(article);
    }

    public Article _findById(Long id){
        return articleRepository.findById(id).get();
    }

    @Override
    public Article update(Article article) {

        //  if (_article!= null)
        //  end the function
        return article;
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Article _article = _findById(id);
        if (_article!= null) {
            articleRepository.deleteById(id);
            return new ResponseEntity<>("Article suprimée", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cet article n'est pas présent", HttpStatus.OK);
    }

    public Article create(String nom, MultipartFile photo, String description, Long idUser) throws IOException {
        Article article = new Article();
        article.setDescription(description);
        article.setNom(nom);
        article.setIdUser(idUser);
        String _photo = Utils.addMultiPartFile("photo",photo);
        article.setPhoto(_photo);
        return articleRepository.save(article);
    }

    public int modifyArticleState(ArticleState articleState, Long id){
        return articleRepository.modifyState(articleState, id);
    }
}
