package com.cx.question.order.model;

import com.cx.question.entity.order.enums.QuestionTypeEnum;
import com.cx.question.entity.reply.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {

    private Long id;

    private String companyName;

    private String departmentName;

    private String contacts;

    private String mobile;

    private QuestionTypeEnum questionType;

    private String questionContent;

    private String acceptNumber;

    private LocalDateTime acceptAt;

    private StatusEnum status;

    private Long replyId;

}
