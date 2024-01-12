package com.remd.remd_articles.controller;

import com.remd.remd_articles.beans.Users;
import com.remd.remd_articles.models.Article;
import com.remd.remd_articles.models.ArticleState;
import com.remd.remd_articles.proxies.NotificationsProxy;
import com.remd.remd_articles.proxies.UserProxy;
import com.remd.remd_articles.services.impl.ArticleService;
import com.remd.remd_articles.utils.Utils;
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
    @Autowired
    private NotificationsProxy notificationsProxy;
    @Autowired
    private UserProxy userProxy;

    @PostMapping("create")
    public ResponseEntity<?> create (@RequestParam("nom") String nom,
                                     @RequestParam("photo")MultipartFile photo,
                                     @RequestParam("description") String description,
                                     @RequestParam("idUser")Long idUser) throws IOException {
        if (Utils.verifyImageExtension(photo)){
            return new ResponseEntity<>("Nous n'acceptions que les images de type jpeg ou alors png", HttpStatus.BAD_REQUEST);
        }
        Article article = articleService.create(nom, photo, description, idUser);
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

//    @GetMapping("{id}")
//    public ResponseEntity<?> getById(@PathVariable("id")Long id){
//        Article article = articleService._findById(id);
//        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(article, HttpStatus.OK);
//    }

    @GetMapping("{id}")
    public Article findById(@PathVariable("id") Long id){
        return  articleService._findById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return articleService.delete(id);
    }

    @PutMapping("perdu/{id}")
    public void marquerPerdu(@PathVariable("id") Long id){
        articleService.modifyArticleState(ArticleState.perdu,id);
    }

    @PutMapping("retrouve/{id}")
    public ResponseEntity<?> marquerRetrouve(@PathVariable("id") Long id){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(ArticleState.retrouve,id);
        Users user = userProxy.getById(article.getIdUser());
        notificationsProxy.sendTrouve(user.getUserName(), user.getEmail().toString());
        return new ResponseEntity<>("Article marque retrouve", HttpStatus.OK);
    }

    @PutMapping("supposeTrouve/{id}/{idUser}")
    public ResponseEntity<?> marquerSupposeTrouve(@PathVariable("id") Long id, @PathVariable("idUser") Long idUser){
        Article article = articleService._findById(id);
        if (article== null) return new ResponseEntity<>("Cet article n'existe pas", HttpStatus.BAD_REQUEST);
        articleService.modifyArticleState(ArticleState.supposeTrouve,id);
        Users user = userProxy.getById(article.getIdUser());
        Users retrouveur = userProxy.getById(idUser);
        notificationsProxy.sendPotentiellementRetrouve(user.getUserName(), user.getEmail().toString(), retrouveur.getUserName(), retrouveur.getNumber(), retrouveur.getEmail().toString());
        return new ResponseEntity<>("Article marque suppose trouve", HttpStatus.OK);
    }
}
