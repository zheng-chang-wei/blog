package com.zcw.blog.controller;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.common.page.PageService;
import com.zcw.blog.common.page.QueryRequest;
import com.zcw.blog.model.Category;
import com.zcw.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcw
 * @date 2020/01/02
 *     <p>Description:
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired CategoryService categoryService;
  @Autowired PageService pageService;

  @GetMapping("/listCategory")
  public ResponseBo listCategory(QueryRequest request, String name) {
    return ResponseBo.ok(
        pageService.selectByPageNumSize(request, () -> categoryService.listCategory(name)));
  }

  @GetMapping("/getCategoryById")
  public ResponseBo getCategoryById(int categoryId) {
    return ResponseBo.ok(categoryService.getCategoryById(categoryId));
  }

  @PostMapping("/insertCategory")
  public ResponseBo insertCategory(Category category) {
    categoryService.insertCategory(category);
    return ResponseBo.ok();
  }

  @PostMapping("/editCategory")
  public ResponseBo editCategory(Category category) {
    categoryService.editCategory(category);
    return ResponseBo.ok();
  }

  @PostMapping("/deleteCategoryById")
  public ResponseBo deleteCategoryById(String[] categoryIds) {
    categoryService.deleteCategoryById(categoryIds);
    return ResponseBo.ok();
  }
}
