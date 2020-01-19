package com.zcw.blog.dao;

import com.github.pagehelper.util.StringUtil;
import com.zcw.blog.config.CommonMapper;
import com.zcw.blog.model.Article;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface ArticleMapper extends CommonMapper<Article> {

  @SelectProvider(type = ArticleMapperProvider.class, method = "listArticles")
  List<Article> listArticles(Integer categoryId, String title);

  class ArticleMapperProvider {

    public String listArticles(Integer categoryId, String title) {
      String sql =
          "SELECT article.id, article.title, article.update_time, category.category_name FROM article "
              + "LEFT JOIN category ON article.category_id = category.id "
              + "LEFT JOIN `user` ON article.user_id = `user`.id "
              + "WHERE true and `user`.id = 1 ";
      if (categoryId != null) {
        sql += " and article.category_id =#{categoryId}";
      }
      if (StringUtil.isNotEmpty(title)) {
        sql += " and article.title LIKE #{title}";
      }
      sql += " ORDER BY article.update_time DESC";
      return sql;
    }
  }
}
