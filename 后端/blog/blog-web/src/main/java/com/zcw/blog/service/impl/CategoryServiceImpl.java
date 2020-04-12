package com.zcw.blog.service.impl;

import java.util.Arrays;
import java.util.List;

import com.zcw.blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcw.blog.dao.CategoryMapper;
import com.zcw.blog.model.Category;
import com.zcw.blog.service.CategoryService;
import tk.mybatis.mapper.entity.Example;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategory(String name) {
        return categoryMapper.listCategory(name);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void editCategory(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void deleteCategoryById(String[] categoryIds) {
        Example example = new Example(Category.class);
        example.createCriteria().andIn("id", Arrays.asList(categoryIds));
        categoryMapper.deleteByExample(example);
    }
}
