package com.zcw.blog.dao;

import com.github.pagehelper.util.StringUtil;
import com.zcw.blog.config.CommonMapper;
import com.zcw.blog.model.Category;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CategoryMapper extends CommonMapper<Category> {

  @SelectProvider(type = CategoryMapperProvider.class, method = "listCategory")
  List<Category> listCategory(String name);

  class CategoryMapperProvider {

    public String listCategory(String name) {
      String sql = "SELECT * from category where true ";
      if (StringUtil.isNotEmpty(name)) {
        sql += " and name LIKE #{name}";
      }
      return sql;
    }
  }
}
