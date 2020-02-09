package com.htf.zdh.jdbc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.htf.zdh.entity.User;

@Mapper
public interface UserMapper {

	@Select("select id , username , password from user where username = #{username}")
	User loadUserByUsername(@Param("username") String username);

}
