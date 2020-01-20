package com.zcw.blog.service.impl;

import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;
import com.zcw.blog.service.ArticleService;
import com.zcw.blog.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

  @Autowired ArticleService articleService;

  @Override
  public List<StatisticsArticleCategoryResponse> statisticsArticleCategory() {
    return articleService.statisticsArticleCategory();
  }

  @Override
  public List<StatisticsArticleByDateResponse> statisticsArticleByDate() {
    return articleService.statisticsArticleByDate();
  }

  @Override
  public List<StatisticsPopularArticlesResponse> statisticsPopularArticles() {
    return articleService.statisticsPopularArticles();
  }
}
