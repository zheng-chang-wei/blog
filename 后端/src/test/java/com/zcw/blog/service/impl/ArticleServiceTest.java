package com.zcw.blog.service.impl;

import com.zcw.blog.BlogApplication;
import com.zcw.blog.model.Article;
import com.zcw.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class ArticleServiceTest {

  @Autowired ArticleService articleService;

  @Test
  public void testListArticles() {}

  @Test
  public void testGetArticleById() {
    Article articleById = articleService.getArticleById(21);
    System.out.println(articleById);
  }

  @Test
  public void testInsertArticle() {
    Article article = new Article();
    article.setCategoryId(1);
    article.setArticleTags("标签");
    article.setContent("内容");
    article.setArticleType((byte) 1);
    article.setCoverImage("封面");
    article.setIsPublic(true);
    article.setIsRecommend(true);
    article.setOpenComment(true);
    article.setTitle("标题");
    article.setUserId(1);
    article.setShowCount(1L);

    articleService.insertArticle(article);
  }

  @Test
  public void testEditArticle() {
    Article article = new Article();
    article.setId(21);
    article.setCategoryId(1);
    article.setArticleTags("标签1");
    article.setContent("内容1");
    article.setArticleType((byte) 1);
    article.setCoverImage("封面1");
    article.setIsPublic(true);
    article.setIsRecommend(true);
    article.setOpenComment(true);
    article.setTitle("标题");
    article.setUserId(1);
    article.setShowCount(1L);
    articleService.editArticle(article);
  }

  @Test
  public void testDeleteArticleById() {
    articleService.deleteArticleById(21);
  }
}
