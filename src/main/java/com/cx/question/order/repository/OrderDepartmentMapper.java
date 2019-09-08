package com.cx.question.order.repository;

import com.cx.question.entity.relation.OrderDepartment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderDepartmentMapper extends Mapper<OrderDepartment> {

    List<Long> getOrderIds(@Param("departmentId") Long departmentId);
}
