package com.zcw.blog.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
public class SysLog {
  @Id
  @GeneratedValue(generator = "JDBC")
  private Long id;

  private String username;

  private Long time;

  private String ip;

  private String operation;

  private String method;

  private String params;

  private Date createTime;

  // 用于搜索条件中的时间字段
  @Transient private String startTime;

  // 用于搜索条件中的时间字段
  @Transient private String endTime;
}
