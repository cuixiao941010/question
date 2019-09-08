package com.cx.question.entity.relation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_order_department")
public class OrderDepartment {

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "order_id")
	private Long orderId;

}
