package com.zcw.blog.service;

import com.zcw.blog.model.Article;
import com.zcw.blog.model.request.ArticleRequest;
import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;

import java.util.List;

public interface ArticleService {

  List<Article> listArticles(ArticleRequest articleRequest);

  Article getArticleById(int articleId);

  void insertArticle(Article article);

  void editArticle(Article article);

  void deleteArticleById(String[] articleIds);

  List<StatisticsArticleCategoryResponse> statisticsArticleCategory();

  List<StatisticsArticleByDateResponse> statisticsArticleByDate();

  List<StatisticsPopularArticlesResponse> statisticsPopularArticles();
}
