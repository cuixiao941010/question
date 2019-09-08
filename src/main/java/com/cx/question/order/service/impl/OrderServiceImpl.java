package com.cx.question.order.service.impl;

import com.cx.question.base.service.impl.BaseServiceImpl;
import com.cx.question.entity.order.Order;
import com.cx.question.entity.relation.OrderDepartment;
import com.cx.question.entity.reply.Reply;
import com.cx.question.entity.reply.enums.StatusEnum;
import com.cx.question.entity.user.User;
import com.cx.question.jwt.UserContext;
import com.cx.question.order.model.OrderCondition;
import com.cx.question.order.model.OrderDTO;
import com.cx.question.order.repository.OrderDepartmentMapper;
import com.cx.question.order.repository.OrderMapper;
import com.cx.question.order.service.OrderService;
import com.cx.question.reply.service.ReplyService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderDepartmentMapper orderDepartmentMapper;

	@Autowired
	private ReplyService replyService;

	@Override
	protected Mapper<Order> getMapper() {
		return orderMapper;
	}

	@Override
	public Long saveOrder(OrderCondition orderCondition) {
		Order order = new Order();
		order.setCompanyName(orderCondition.getCompanyName());
		order.setContacts(orderCondition.getContacts());
		order.setMobile(orderCondition.getMobile());
		order.setQuestionType(orderCondition.getQuestionType());
		order.setQuestionContent(orderCondition.getQuestionContent());
		order.setAcceptAt(LocalDateTime.now());
		order.setAcceptNumber(UUID.randomUUID().toString());
		orderMapper.insert(order);
		Long orderId = order.getId();

		for (Long departmentId : orderCondition.getDepartmentIds()) {
			OrderDepartment orderDepartment = new OrderDepartment();
			orderDepartment.setOrderId(orderId);
			orderDepartment.setDepartmentId(departmentId);
			orderDepartmentMapper.insert(orderDepartment);

			Reply reply = new Reply();
			reply.setDepartmentId(departmentId);
			reply.setOrderId(orderId);
			reply.setStatus(StatusEnum.UnHandle);
			replyService.save(reply);
		}
		return orderId;
	}

	@Override
	public List<OrderDTO> getOrders(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		Long departmentId = UserContext.getCurrentUser().getDepartmentId();
		List<Long> orderIds = orderDepartmentMapper.getOrderIds(departmentId);
		if (CollectionUtils.isEmpty(orderIds)) {
			log.info("======当前部门无订单信息======");
			return null;
		}
		List<OrderDTO> orderDTOS = orderMapper.getOrders(departmentId, orderIds);
		return orderDTOS;
	}

}
