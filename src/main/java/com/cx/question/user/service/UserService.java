package com.cx.question.user.service;



import com.cx.question.base.service.BaseService;
import com.cx.question.entity.user.User;
import com.cx.question.user.model.LoginCondition;
import com.cx.question.user.model.LoginDTO;
import com.cx.question.user.model.RegisterCondition;
import com.cx.question.user.model.UsersDTO;

import java.util.List;

;

public interface UserService extends BaseService<User, Long> {

    LoginDTO login(LoginCondition loginCondition);

    void logout();

    void register(RegisterCondition registerCondition);

    List<UsersDTO> getUsers(Integer page, Integer pageSize, String userName, String mobile);

    void deleteUser(Long userId);

}
