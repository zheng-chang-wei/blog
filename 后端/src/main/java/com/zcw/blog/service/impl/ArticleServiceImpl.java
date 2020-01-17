package com.zcw.blog.service.impl;

import com.zcw.blog.dao.ArticleMapper;
import com.zcw.blog.model.Article;
import com.zcw.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired ArticleMapper articleMapper;

  @Override
  public List<Article> listArticles() {
    return articleMapper.selectAll();
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
  public void deleteArticleById(int articleId) {
    articleMapper.deleteByPrimaryKey(articleId);
  }
}
