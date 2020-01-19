package com.zcw.blog.service.impl;

import com.zcw.blog.dao.CategoryMapper;
import com.zcw.blog.model.Category;
import com.zcw.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

  @Autowired CategoryMapper categoryMapper;

  @Override
  public List<Category> listCategory(String name) {
    return categoryMapper.listCategory(name);
  }

  @Override
  public Category getCategoryById(int categoryId) {
    return selectByKey(categoryId);
  }

  @Override
  public void insertCategory(Category category) {
    save(category);
  }

  @Override
  public void editCategory(Category category) {
    updateNotNull(category);
  }

  @Override
  public void deleteCategoryById(String[] categoryIds) {
    batchDelete(Arrays.asList(categoryIds), "id", Category.class);
  }
}
