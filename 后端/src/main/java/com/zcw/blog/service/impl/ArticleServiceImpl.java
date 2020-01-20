package com.zcw.blog.service.impl;

import com.zcw.blog.dao.ArticleMapper;
import com.zcw.blog.model.Article;
import com.zcw.blog.model.request.ArticleRequest;
import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;
import com.zcw.blog.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

  @Autowired ArticleMapper articleMapper;

  @Override
  public List<Article> listArticles(ArticleRequest articleRequest) {
    String title = articleRequest.getTitle();
    if (StringUtils.isNotEmpty(title)) {
      title = "%" + title + "%";
    }
    return articleMapper.listArticles(articleRequest);
  }

  @Override
  public Article getArticleById(int articleId) {
    return selectByKey(articleId);
  }

  @Override
  public void insertArticle(Article article) {
    article.setUserId(1);
    article.setShowCount(0L);
    article.setIsRecommend(false);
    save(article);
  }

  @Override
  public void editArticle(Article article) {
    updateNotNull(article);
  }

  @Override
  public void deleteArticleById(String[] articleIds) {
    batchDelete(Arrays.asList(articleIds), "id", Article.class);
  }

  @Override
  public List<StatisticsArticleCategoryResponse> statisticsArticleCategory() {
    return articleMapper.statisticsArticleCategory();
  }

  @Override
  public List<StatisticsArticleByDateResponse> statisticsArticleByDate() {
    return articleMapper.statisticsArticleByDate();
  }

  @Override
  public List<StatisticsPopularArticlesResponse> statisticsPopularArticles() {
    return articleMapper.statisticsPopularArticles();
  }
}
