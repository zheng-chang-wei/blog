package com.zcw.blog.dao;

import com.zcw.blog.config.CommonMapper;
import com.zcw.blog.model.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends CommonMapper<SysUser> {

  @Select("select * from sys_user where username = #{username}")
  @Results({
    @Result(id = true, property = "id", column = "id"),
    @Result(
        property = "roles",
        column = "id",
        javaType = List.class,
        many = @Many(select = "com.zcw.blog.dao.RoleMapper.findByUid"))
  })
  public SysUser findByName(String username);
}
