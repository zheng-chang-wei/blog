package com.zcw.blog.service;

import com.zcw.blog.model.Category;

import java.util.List;

public interface CategoryService {
  List<Category> listCategory(String name);

  Category getCategoryById(int categoryId);

  void insertCategory(Category category);

  void editCategory(Category category);

  void deleteCategoryById(String[] categoryIds);
}
