package com.cx.question.user.api;

import com.cx.question.base.model.PageDTO;
import com.cx.question.user.model.LoginCondition;
import com.cx.question.user.model.LoginDTO;
import com.cx.question.user.model.RegisterCondition;
import com.cx.question.user.model.UsersDTO;
import com.cx.question.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("login")
	public LoginDTO login(@RequestBody @Valid LoginCondition loginCondition) {
		return userService.login(loginCondition);
	}

	@PostMapping("logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout() {
		userService.logout();
	}

	@PostMapping("register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void register(@RequestBody @Valid RegisterCondition registerCondition) {
		userService.register(registerCondition);
	}

	@GetMapping("users")
	public PageDTO getUsers(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
							@RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize,
							@RequestParam(value = "userName", required = false) String userName,
							@RequestParam(value = "mobile", required = false) String mobile) {
		List<UsersDTO> usersDTOS = userService.getUsers(page, pageSize, userName, mobile);
		return new PageDTO(new PageInfo(usersDTOS), usersDTOS);
	}

	@PutMapping("delete")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@RequestParam(value = "userId") Long userId) {
		userService.deleteUser(userId);
	}

}
