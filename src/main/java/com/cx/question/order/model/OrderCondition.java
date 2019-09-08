package com.cx.question.order.model;

import com.cx.question.entity.order.enums.QuestionTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class OrderCondition {

    @NotBlank(message = "企业名称不能为空")
    private String companyName;

    @NotEmpty(message = "请选择部门")
    private List<Long> departmentIds;

    @NotBlank(message = "联系人不能为空")
    private String contacts;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式不正确")
    private String mobile;

    @NotNull(message = "请选择问题类型")
    private QuestionTypeEnum questionType;

    @NotBlank(message = "问题描述不能为空")
    private String questionContent;

}
