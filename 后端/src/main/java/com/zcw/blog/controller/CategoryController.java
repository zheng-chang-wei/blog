package com.zcw.blog.controller;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.common.page.PageService;
import com.zcw.blog.common.page.QueryRequest;
import com.zcw.blog.model.Category;
import com.zcw.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoryController {

  @Autowired CategoryService categoryService;
  @Autowired PageService pageService;

  @GetMapping("/listCategory")
  public ResponseBo listCategory(QueryRequest request, String name) {
    try {
      return ResponseBo.ok(
          pageService.selectByPageNumSize(request, () -> categoryService.listCategory(name)));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("查询失败");
    }
  }

  @GetMapping("/getCategoryById")
  public ResponseBo getCategoryById(int categoryId) {
    try {
      return ResponseBo.ok(categoryService.getCategoryById(categoryId));
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("查询失败");
    }
  }

  @PostMapping("/insertCategory")
  public ResponseBo insertCategory(Category category) {
    try {
      categoryService.insertCategory(category);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("添加失败");
    }
  }

  @PostMapping("/editCategory")
  public ResponseBo editCategory(Category category) {
    try {
      categoryService.editCategory(category);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("编辑失败");
    }
  }

  @PostMapping("/deleteCategoryById")
  public ResponseBo deleteCategoryById(String[] categoryIds) {
    try {
      categoryService.deleteCategoryById(categoryIds);
      return ResponseBo.ok();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return ResponseBo.error("删除失败");
    }
  }
}
