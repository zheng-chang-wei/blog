package com.zcw.blog.controller;

import com.zcw.blog.common.model.ResponseBo;
import com.zcw.blog.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

  @Autowired StatisticsService statisticsService;

  /**
   * 统计文章分类
   *
   * @return
   */
  @GetMapping("/statisticsArticleCategory")
  public ResponseBo statisticsArticleCategory() {
    return ResponseBo.ok(statisticsService.statisticsArticleCategory());
  }

  @GetMapping("/statisticsArticleByDate")
  public ResponseBo statisticsArticleByDate() {
    return ResponseBo.ok(statisticsService.statisticsArticleByDate());
  }

  @GetMapping("/statisticsPopularArticles")
  public ResponseBo statisticsPopularArticles() {
    return ResponseBo.ok(statisticsService.statisticsPopularArticles());
  }
}
