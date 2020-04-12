package com.zcw.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zcw.blog.common.config.CommonMapper;
import com.zcw.blog.model.SysUser;

public interface UserMapper extends CommonMapper<SysUser> {

	@Select("select * from sys_user where username = #{username}")
	@Results({ @Result(id = true, property = "id", column = "id"),
			@Result(property = "roles", column = "id", javaType = List.class, many = @Many(select = "com.zcw.blog.dao.RoleMapper.findByUid")) })
	public SysUser findByName(String username);
}
