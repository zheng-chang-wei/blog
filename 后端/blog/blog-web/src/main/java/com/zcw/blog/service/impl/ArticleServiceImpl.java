package com.zcw.blog.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcw.blog.dao.ArticleMapper;
import com.zcw.blog.model.Article;
import com.zcw.blog.model.request.ArticleRequest;
import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;
import com.zcw.blog.service.ArticleService;
import tk.mybatis.mapper.entity.Example;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;

	@Override
	public List<Article> listArticles(ArticleRequest articleRequest) {
		String title = articleRequest.getTitle();
		if (StringUtils.isNotEmpty(title)) {
			title = "%" + title + "%";
			articleRequest.setTitle(title);
		}
		return articleMapper.listArticles(articleRequest);
	}

	@Override
	public Article getArticleById(int articleId) {
		return articleMapper.selectByPrimaryKey(articleId);
	}

	@Override
	public void insertArticle(Article article) {
		article.setUserId(1);
		article.setShowCount(0L);
		article.setIsRecommend(false);
		articleMapper.insert(article);
	}

	@Override
	public void editArticle(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public void deleteArticleById(String[] articleIds) {
		Example example = new Example(Article.class);
		example.createCriteria().andIn("id", Arrays.asList(articleIds));
		articleMapper.deleteByExample(example);
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
