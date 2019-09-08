package com.cx.question.user.service.impl;

import com.cx.question.base.service.impl.BaseServiceImpl;
import com.cx.question.entity.user.User;
import com.cx.question.exception.BusinessException;
import com.cx.question.exception.ResultEnum;
import com.cx.question.jwt.JwtToken;
import com.cx.question.jwt.UserContext;
import com.cx.question.redis.service.RedisService;
import com.cx.question.user.model.LoginCondition;
import com.cx.question.user.model.LoginDTO;
import com.cx.question.user.model.RegisterCondition;
import com.cx.question.user.model.UsersDTO;
import com.cx.question.user.repository.UserMapper;
import com.cx.question.user.service.UserService;
import com.cx.question.util.Digests;
import com.cx.question.util.Encodes;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.UUID;

import static com.cx.question.util.Digests.HASH_INTERATIONS;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisService redisService;

	@Override
	protected Mapper<User> getMapper() {
		return userMapper;
	}

	@Override
	public LoginDTO login(LoginCondition loginCondition) {
		User user = new User();
		user.setUserName(loginCondition.getUserName());
		User userSearch = userMapper.selectOne(user);
		if (userSearch == null) {
			throw new BusinessException(ResultEnum.USER_IS_NOT_EXIT);
		}
		byte[] hashPassword = Digests.md5(loginCondition.getPassword().getBytes(), userSearch.getSalt().getBytes(), HASH_INTERATIONS);
		String hashPsw = Encodes.encodeHex(hashPassword);
		if (!hashPsw.equals(userSearch.getPassWord())) {
			throw new BusinessException(ResultEnum.USERNAME_OR_PASSWORD_ERROR);
		}
		String token = JwtToken.createToken(userSearch.getId(), userSearch.getDepartmentId(), userSearch.getUserName());
		UserContext.AuthorizedUser authorizedUser = new UserContext.AuthorizedUser(userSearch.getId(), userSearch.getDepartmentId(), userSearch.getUserName());
		new UserContext(authorizedUser);
//		redisService.setValue(String.valueOf(userSearch.getId()), token);
		return new LoginDTO(userSearch.getId(), userSearch.getDepartmentId(), token, userSearch.getUserName());
	}

	@Override
	public void logout() {
		Long userId = UserContext.getCurrentUser().getId();
		if (userId == null) {
			return;
		}
		redisService.deleteValue(String.valueOf(userId));
	}

	@Override
	public void register(RegisterCondition registerCondition) {
		User user = new User();
		user.setUserName(registerCondition.getUserName());
		User userSearch = userMapper.selectOne(user);
		if (userSearch != null) {
			throw new BusinessException(ResultEnum.USERNAME_IS_EXISTED);
		}
		String salt = UUID.randomUUID().toString().replace("-","");
		byte[] saltByte = salt.getBytes();
		byte[] hashPassword = Digests.md5(registerCondition.getPassword().getBytes(), saltByte, HASH_INTERATIONS);
		String encryPassword = Encodes.encodeHex(hashPassword);
		User userToInsert = new User();
		userToInsert.setUserName(registerCondition.getUserName());
		userToInsert.setPassWord(encryPassword);
		userToInsert.setSalt(salt);
		//TODO 默认新增普通类型用户，后期可加权限控制
//		userToInsert.setRole(RoleEnum.Simple);
//		userToInsert.setEmail(registerCondition.getEmail());
//		userToInsert.setMobile(registerCondition.getMobile());
		userMapper.insertSelective(userToInsert);
	}

	@Override
	public List<UsersDTO> getUsers(Integer page, Integer pageSize, String userName, String mobile) {
		PageHelper.startPage(page, pageSize);
		List<UsersDTO> usersDTOS = null;
//		if (RoleEnum.Simple.equals(UserContext.getCurrentUser().getRole())) {
//			usersDTOS = userMapper.getUsers(null, UserContext.getCurrentUser().getId(), userName, mobile);
//		}else if (RoleEnum.Manager.equals(UserContext.getCurrentUser().getRole())) {
//			usersDTOS = userMapper.getUsers(RoleEnum.Simple, UserContext.getCurrentUser().getId(), userName, mobile);
//		}
		return usersDTOS;
	}

	@Override
	public void deleteUser(Long userId) {
		Long currentUserId = UserContext.getCurrentUser().getId();
		if (userId.equals(currentUserId)) {
			throw new BusinessException(ResultEnum.DONT_DELETE_SELF);
		}
//		if (RoleEnum.Simple.equals(UserContext.getCurrentUser().getRole())) {
//			throw new BusinessException(ResultEnum.CANT_DELETE);
//		}
		User userSearch = userMapper.selectByPrimaryKey(userId);
		if (userSearch == null) {
			throw new BusinessException(ResultEnum.USER_IS_NOT_EXIT);
		}
		userMapper.delete(userSearch);
	}

}
