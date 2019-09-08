package com.cx.question.entity.reply.enums;

import com.cx.question.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum OverTimeEnum implements BaseEnum {

	NotOverTime(1, "未超时"),
	OverTime(2, "已超时");

	private Integer value;

	private String text;

	OverTimeEnum(Integer value, String text) {
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
	public static OverTimeEnum valueOf(Integer value) {
		return BaseEnum.valueOf(OverTimeEnum.class, value);
	}
}
