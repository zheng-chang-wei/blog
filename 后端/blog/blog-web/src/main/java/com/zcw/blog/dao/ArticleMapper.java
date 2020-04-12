package com.zcw.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.github.pagehelper.util.StringUtil;
import com.zcw.blog.common.config.CommonMapper;
import com.zcw.blog.model.Article;
import com.zcw.blog.model.request.ArticleRequest;
import com.zcw.blog.model.response.StatisticsArticleByDateResponse;
import com.zcw.blog.model.response.StatisticsArticleCategoryResponse;
import com.zcw.blog.model.response.StatisticsPopularArticlesResponse;

public interface ArticleMapper extends CommonMapper<Article> {

	@SelectProvider(type = ArticleMapperProvider.class, method = "listArticles")
	List<Article> listArticles(ArticleRequest articleRequest);

	@Select(" SELECT category.id,category.category_name,COUNT(article.id) count from article LEFT JOIN category ON article.category_id=category.id GROUP BY category_id")
	List<StatisticsArticleCategoryResponse> statisticsArticleCategory();

	@Select("SELECT date_format(update_time, '%Y年%m月') AS updateTime, COUNT(id) count FROM article GROUP BY date_format(update_time, '%Y年%m月') ORDER BY update_time DESC")
	List<StatisticsArticleByDateResponse> statisticsArticleByDate();

	@Select("SELECT id as article_id,title,show_count from article WHERE show_count !=0 ORDER BY show_count DESC LIMIT 5")
	List<StatisticsPopularArticlesResponse> statisticsPopularArticles();

	class ArticleMapperProvider {

		public String listArticles(ArticleRequest articleRequest) {
			String sql = "SELECT article.id, article.title, article.update_time, article.show_count, article.cover_image, category.category_name FROM article "
					+ "LEFT JOIN category ON article.category_id = category.id " + "LEFT JOIN `sys_user` ON article.user_id = `sys_user`.id "
					+ "WHERE true and `sys_user`.id = 1 ";
			if (articleRequest.getCategoryId() != null) {
				sql += " and article.category_id =#{categoryId}";
			}
			if (StringUtil.isNotEmpty(articleRequest.getTitle())) {
				sql += " and article.title LIKE #{title}";
			}
			if (StringUtil.isNotEmpty(articleRequest.getUpdateTime())) {
				sql += " AND date_format(update_time, '%Y年%m月') = #{updateTime}";
			}
			sql += " ORDER BY article.update_time DESC";
			return sql;
		}
	}
}
