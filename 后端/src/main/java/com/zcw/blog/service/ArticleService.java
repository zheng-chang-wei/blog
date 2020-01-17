package com.zcw.blog.service;

import java.util.List;

import com.zcw.blog.model.Article;

public interface ArticleService {

	List<Article> listArticles();

	Article getArticleById(int articleId);

	void insertArticle(Article article);

	void editArticle(Article article);

	void deleteArticleById(int articleId);

}
