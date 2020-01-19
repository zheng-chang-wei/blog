package com.zcw.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
public class Article {

  @Id
  @GeneratedValue(generator = "JDBC")
  private Integer id;

  private Integer categoryId;

  @Transient private String categoryName;

  private Integer userId;

  private String title;

  private String articleTags;

  private Boolean isPublic;

  private Long showCount;

  private Boolean openComment;

  private String coverImage;

  private Boolean isRecommend;

  private Byte articleType;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  private String content;
}
