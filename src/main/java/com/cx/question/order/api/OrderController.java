package com.cx.question.order.api;

import com.cx.question.base.model.PageDTO;
import com.cx.question.order.model.OrderCondition;
import com.cx.question.order.model.OrderDTO;
import com.cx.question.order.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("save")
	public Long executeActivation(@RequestBody @Valid OrderCondition orderCondition) {
		return orderService.saveOrder(orderCondition);
	}

	@GetMapping
	public PageDTO getOrders(@RequestParam(value = "page", defaultValue = "1") Integer page,
							 @RequestParam(value = "limit", defaultValue = "10") Integer pageSize) {
		List<OrderDTO> orderDTOS = orderService.getOrders(page, pageSize);
		if (CollectionUtils.isEmpty(orderDTOS)) {
			return new PageDTO();
		}
		return new PageDTO(new PageInfo(orderDTOS), orderDTOS);
	}

}
