package com.cx.question.entity.order.enums;

import com.cx.question.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuestionTypeEnum implements BaseEnum {

	LandDemand(1, "土地需求"),
	FundDemand(2, "资金需求"),
	PolicySupport(3, "政策扶持"),
	Recruit(4, "人才招工"),
	GuaranteeProduction(5, "保障生产要素"),
	Security(6, "安全保障"),
	Approval(7, "行政审批"),
	Environmental(8, "环境保护"),
	SafetyProduction(9, "安全生产");

	private Integer value;

	private String text;

	QuestionTypeEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	@Override
	public int value() {
		return value;
	}

	/**
	 * mybatis Mapper中的方法参数如果为枚举的话, 会调用toString()方法获取枚举值, 用于ognl表达式判断
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	@SuppressWarnings("unused")
	@JsonCreator
	public static QuestionTypeEnum valueOf(Integer value) {
		return BaseEnum.valueOf(QuestionTypeEnum.class, value);
	}
}
