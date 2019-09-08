package com.cx.question.reply.api;

import com.cx.question.base.model.PageDTO;
import com.cx.question.exception.ResponseData;
import com.cx.question.order.model.OrderCondition;
import com.cx.question.order.model.OrderDTO;
import com.cx.question.order.service.OrderService;
import com.cx.question.reply.model.ReplyCondition;
import com.cx.question.reply.model.ReplyDTO;
import com.cx.question.reply.service.ReplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@PutMapping("{id}/handle")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeActivation(@PathVariable("id") Long id, @RequestBody @Valid ReplyCondition replyCondition) {
		replyService.handleReply(id, replyCondition);
	}

	@GetMapping("{id}/detail")
	public ResponseData getRepyDetail(@PathVariable("id") Long id) {

		ReplyDTO replyDTO = replyService.getReplyDetail(id);
		return new ResponseData(replyDTO);
	}

}
