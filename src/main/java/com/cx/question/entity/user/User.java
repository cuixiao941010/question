package com.cx.question.entity.user;

import com.cx.question.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_user")
public class User extends BaseEntity {

	@Column(name = "user_name")
	private String userName;

	@Column(name = "pass_word")
	private String passWord;

	@Column(name = "salt")
	private String salt;

	@Column(name = "department_id")
	private Long departmentId;

}
