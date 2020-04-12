package com.zcw.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.github.pagehelper.util.StringUtil;
import com.zcw.blog.common.config.CommonMapper;
import com.zcw.blog.model.Category;

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
