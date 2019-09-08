package com.cx.question.entity.department;

import com.cx.question.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_department")
public class Department extends BaseEntity {

	@Column(name = "name")
	private String name;

}
