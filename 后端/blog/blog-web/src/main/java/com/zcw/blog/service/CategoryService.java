package com.zcw.blog.service;

import java.util.List;

import com.zcw.blog.model.Category;

public interface CategoryService {

	List<Category> listCategory(String name);

	Category getCategoryById(int categoryId);

	void insertCategory(Category category);

	void editCategory(Category category);

	void deleteCategoryById(String[] categoryIds);
}
