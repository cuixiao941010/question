package com.cx.question.reply.model;

import com.cx.question.entity.order.enums.QuestionTypeEnum;
import com.cx.question.entity.reply.enums.EvaluateEnum;
import com.cx.question.entity.reply.enums.MethodEnum;
import com.cx.question.entity.reply.enums.OverTimeEnum;
import com.cx.question.entity.reply.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDTO {

    private Long replyId;

    private String companyName;

    private String departmentName;

    private String contacts;

    private String mobile;

    private QuestionTypeEnum questionType;

    private String questionContent;

    private String acceptNumber;

    private LocalDateTime acceptAt;

    private StatusEnum status;

    private MethodEnum method;

    private String replyContent;

    private LocalDateTime endAt;

    private OverTimeEnum overTime;

    private Integer urgeTimes;

    private EvaluateEnum evaluate;

    private String reason;

    private String returnContent;

    private LocalDateTime returnAt;

}
