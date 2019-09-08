package com.cx.question.entity.reply;

import com.cx.question.base.entity.BaseEntity;
import com.cx.question.entity.reply.enums.EvaluateEnum;
import com.cx.question.entity.reply.enums.MethodEnum;
import com.cx.question.entity.reply.enums.OverTimeEnum;
import com.cx.question.entity.reply.enums.StatusEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "t_reply")
public class Reply extends BaseEntity {

	@Column(name = "order_id")
	private Long orderId;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "method")
	private MethodEnum method;

	@Column(name = "status")
	private StatusEnum status;

	@Column(name = "reply_content")
	private String replyContent;

	@Column(name = "end_at")
	private LocalDateTime endAt;

	@Column(name = "is_overtime")
	private OverTimeEnum overTime;

	@Column(name = "urge_times")
	private Integer urgeTimes;

	@Column(name = "evaluate")
	private EvaluateEnum evaluate;

	@Column(name = "reason")
	private String reason;

	@Column(name = "return_content")
	private String returnContent;

	@Column(name = "return_at")
	private LocalDateTime returnAt;

}
