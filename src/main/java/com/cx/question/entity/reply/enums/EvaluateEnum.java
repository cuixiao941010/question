package com.cx.question.entity.reply.enums;

import com.cx.question.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EvaluateEnum implements BaseEnum {

	Satisfied(1, "满意"),
	UnSatisfied(2, "不满意");

	private Integer value;

	private String text;

	EvaluateEnum(Integer value, String text) {
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
	public static EvaluateEnum valueOf(Integer value) {
		return BaseEnum.valueOf(EvaluateEnum.class, value);
	}
}
