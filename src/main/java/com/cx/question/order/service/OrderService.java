package com.cx.question.order.service;


import com.cx.question.base.service.BaseService;
import com.cx.question.entity.order.Order;
import com.cx.question.order.model.OrderCondition;
import com.cx.question.order.model.OrderDTO;

import java.util.List;

public interface OrderService extends BaseService<Order, Long> {

    Long saveOrder(OrderCondition orderCondition);

    List<OrderDTO> getOrders(Integer page, Integer PageSize);


}
