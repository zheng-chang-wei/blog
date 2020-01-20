package com.zcw.blog.model.response;

import lombok.Data;

@Data
public class StatisticsPopularArticlesResponse {

  private Integer articleId;

  private String title;

  private Integer showCount;
}
