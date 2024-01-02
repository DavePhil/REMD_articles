package com.remd.remd_articles.controller;

import com.remd.remd_articles.models.Article;
import com.remd.remd_articles.models.ArticleState;
import com.remd.remd_articles.services.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("articles/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("create")
    public ResponseEntity<?> create (@RequestParam("nom") String nom,
                                     @RequestParam("photo")MultipartFile photo,
                                     @RequestParam("description") String description) throws IOException {
        if (!photo.getContentType().equals("image/jpeg") && !photo.getContentType().equals("image/png")){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        Article article = articleService.create(nom, photo, description);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<?> update (@PathVariable("id") Long id){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        Article _article = articleService.update(article);
        return new ResponseEntity<>(_article, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        List<Article> articles = articleService.getAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long id){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }

    @PutMapping("perdu/{id}")
    public void marquerPerdu(@PathVariable Long id){
        articleService.modifyArticleState(ArticleState.perdu,id);
    }

    @PutMapping("retrouve/{id}")
    public ResponseEntity<?> marquerRetrouve(@PathVariable Long id){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(ArticleState.retrouve,id);
        return new ResponseEntity<>("Article marque retrouve", HttpStatus.OK);
    }

    @PutMapping("supposeTrouve/{id}")
    public ResponseEntity<?> marquerSupposeTrouve(@PathVariable Long id){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(ArticleState.supposeTrouve,id);
        return new ResponseEntity<>("Article marque suppose trouve", HttpStatus.OK);
    }
}
