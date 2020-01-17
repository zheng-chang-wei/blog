package com.zcw.blog.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class User {
  @Id
  @GeneratedValue(generator = "JDBC")
  private Integer id;

  private String userName;

  private String password;

  private String avatar;

  private String nickname;

  private String phone;

  private String email;

  private String signature;

  private String address;

  private String workExperience;

  private String qq;

  private String wechatQrCode;

  private String wechat;
}
