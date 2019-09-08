package com.cx.question.entity.reply.enums;

import com.cx.question.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusEnum implements BaseEnum {

	UnHandle(1, "未处理"),
	IsHandled(2, "已处理");

	private Integer value;

	private String text;

	StatusEnum(Integer value, String text) {
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
	public static StatusEnum valueOf(Integer value) {
		return BaseEnum.valueOf(StatusEnum.class, value);
	}
}
