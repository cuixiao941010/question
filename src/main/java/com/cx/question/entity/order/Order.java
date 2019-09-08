package com.cx.question.entity.order;

import com.cx.question.base.entity.BaseEntity;
import com.cx.question.entity.order.enums.QuestionTypeEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "t_order")
public class Order extends BaseEntity {

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contacts")
	private String contacts;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "question_type")
	private QuestionTypeEnum questionType;

	@Column(name = "question_content")
	private String questionContent;

	@Column(name = "accept_number")
	private String acceptNumber;

	@Column(name = "accept_at")
	private LocalDateTime acceptAt;

	//TODO 类型未知
	@Column(name = "accept_type")
	private Integer acceptType;

}
