package com.zcw.blog.service;

import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;

import java.util.List;

public interface StatisticsService {

  List<StatisticsArticleCategoryResponse> statisticsArticleCategory();

  List<StatisticsArticleByDateResponse> statisticsArticleByDate();

  List<StatisticsPopularArticlesResponse> statisticsPopularArticles();
}
