package com.zcw.blog.controller;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.common.page.PageService;
import com.zcw.blog.common.page.QueryRequest;
import com.zcw.blog.model.Article;
import com.zcw.blog.model.request.ArticleRequest;
import com.zcw.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

  @Autowired ArticleService articleService;

  @Autowired PageService pageService;

  @GetMapping("/listArticles")
  public ResponseBo listArticles(QueryRequest request, ArticleRequest articleRequest) {
    return ResponseBo.ok(
        pageService.selectByPageNumSize(
            request, () -> articleService.listArticles(articleRequest)));
  }

  @GetMapping("/getArticleById")
  public ResponseBo getArticleById(int articleId) {
    return ResponseBo.ok(articleService.getArticleById(articleId));
  }

  @PostMapping("/insertArticle")
  public ResponseBo insertArticle(Article article) {
    articleService.insertArticle(article);
    return ResponseBo.ok();
  }

  @PostMapping("/editArticle")
  public ResponseBo editArticle(Article article) {
    articleService.editArticle(article);
    return ResponseBo.ok();
  }

  @PostMapping("/deleteArticleById")
  public ResponseBo deleteArticleById(String[] articleIds) {
    articleService.deleteArticleById(articleIds);
    return ResponseBo.ok();
  }
}
