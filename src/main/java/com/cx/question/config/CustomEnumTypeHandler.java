package com.cx.question.config;


import com.cx.question.base.enums.BaseEnum;
import com.cx.question.entity.order.enums.QuestionTypeEnum;
import com.cx.question.entity.reply.enums.EvaluateEnum;
import com.cx.question.entity.reply.enums.MethodEnum;
import com.cx.question.entity.reply.enums.OverTimeEnum;
import com.cx.question.entity.reply.enums.StatusEnum;
import com.cx.question.entity.user.enums.RoleEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@MappedTypes({
        RoleEnum.class,
        QuestionTypeEnum.class,
        StatusEnum.class,
        OverTimeEnum.class,
        EvaluateEnum.class,
        MethodEnum.class
})
@SuppressWarnings("unused")
public class CustomEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private Class<E> type;

    public CustomEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.value());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return BaseEnum.getEnum(type, i);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by int value.", ex);
            }
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            try {
                return BaseEnum.getEnum(type, i);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by int value.", ex);
            }
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            try {
                return BaseEnum.getEnum(type, i);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by int value.", ex);
            }
        }
    }
}