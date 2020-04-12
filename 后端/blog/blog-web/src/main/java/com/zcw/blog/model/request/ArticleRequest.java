package com.zcw.blog.model.request;

import lombok.Data;

@Data
public class ArticleRequest {
  private Integer categoryId;
  private String title;
  private String updateTime;
}
