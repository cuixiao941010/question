package com.cx.question.order.repository;

import com.cx.question.entity.order.Order;
import com.cx.question.order.model.OrderDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    List<OrderDTO> getOrders(@Param("departmentId") Long departmentId, @Param("orderIds") List<Long> orderIds);
}
