package com.zcw.blog.controller;

import com.zcw.blog.model.Article;
import com.zcw.blog.model.ResponseBo;
import com.zcw.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

  @Autowired ArticleService articleService;

  @GetMapping("/listArticles")
  public ResponseBo listArticles(int categoryId, String title) {
    try {
      return ResponseBo.ok(articleService.listArticles());
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("查询失败");
    }
  }

  @GetMapping("/getArticleById")
  public ResponseBo getArticleById(int articleId) {
    try {
      return ResponseBo.ok(articleService.getArticleById(articleId));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("查询失败");
    }
  }

  @PostMapping("/insertArticle")
  public ResponseBo insertArticle(Article article) {
    try {
      articleService.insertArticle(article);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("发布失败");
    }
  }

  @PostMapping("/editArticle")
  public ResponseBo editArticle(Article article) {
    try {
      articleService.editArticle(article);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("编辑失败");
    }
  }

  @PostMapping("/deleteArticleById")
  public ResponseBo deleteArticleById(int articleId) {
    try {
      articleService.deleteArticleById(articleId);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("删除失败");
    }
  }
}
