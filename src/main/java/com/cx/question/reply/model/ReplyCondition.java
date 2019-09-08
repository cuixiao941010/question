package com.cx.question.reply.model;

import com.cx.question.entity.reply.enums.EvaluateEnum;
import com.cx.question.entity.reply.enums.MethodEnum;
import com.cx.question.entity.reply.enums.OverTimeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ReplyCondition {

    @NotNull(message = "请选择处理方式")
    private MethodEnum method;

    @NotBlank(message = "回复内容不能为空")
    private String replyContent;

    @NotNull(message = "请选择办结时间")
    private LocalDateTime endAt;

    @NotNull(message = "请选择办是否超时")
    private OverTimeEnum overTime;

    @NotNull(message = "请填写催办次数")
    private Integer urgeTimes;

    @NotNull(message = "请选择办结评价")
    private EvaluateEnum evaluate;

    @NotBlank(message = "原因不能为空")
    private String reason;

    @NotBlank(message = "回访内容不能为空")
    private String returnContent;

    @NotNull(message = "回访时间不能为空")
    private LocalDateTime returnAt;

}
