package com.zcw.blog.service;

import com.zcw.blog.model.Article;

import java.util.List;

public interface ArticleService {

  List<Article> listArticles(Integer categoryId, String title);

  Article getArticleById(int articleId);

  void insertArticle(Article article);

  void editArticle(Article article);

  void deleteArticleById(String[] articleIds);
}
