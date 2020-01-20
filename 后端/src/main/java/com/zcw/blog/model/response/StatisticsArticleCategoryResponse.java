package com.zcw.blog.model.response;

import lombok.Data;

@Data
public class StatisticsArticleCategoryResponse {
  private Integer id;
  private String categoryName;
  private Integer count;
}
