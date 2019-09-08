package com.cx.question.user.repository;

import com.cx.question.entity.user.User;
import com.cx.question.entity.user.enums.RoleEnum;
import com.cx.question.user.model.UsersDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<UsersDTO> getUsers(@Param("role") RoleEnum role, @Param("id") Long id, @Param("userName") String userName, @Param("mobile") String mobile);
}
