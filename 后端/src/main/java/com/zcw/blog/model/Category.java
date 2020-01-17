package com.zcw.blog.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Category {
  @Id
  @GeneratedValue(generator = "JDBC")
  private Integer id;

  private String categoryName;
}
