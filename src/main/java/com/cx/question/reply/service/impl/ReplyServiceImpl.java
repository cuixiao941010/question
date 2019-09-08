package com.cx.question.reply.service.impl;

import com.cx.question.base.service.impl.BaseServiceImpl;
import com.cx.question.entity.order.Order;
import com.cx.question.entity.relation.OrderDepartment;
import com.cx.question.entity.reply.Reply;
import com.cx.question.entity.reply.enums.StatusEnum;
import com.cx.question.exception.BusinessException;
import com.cx.question.exception.ResultEnum;
import com.cx.question.jwt.UserContext;
import com.cx.question.order.model.OrderCondition;
import com.cx.question.order.model.OrderDTO;
import com.cx.question.order.repository.OrderDepartmentMapper;
import com.cx.question.order.repository.OrderMapper;
import com.cx.question.order.service.OrderService;
import com.cx.question.reply.model.ReplyCondition;
import com.cx.question.reply.model.ReplyDTO;
import com.cx.question.reply.repository.ReplyMapper;
import com.cx.question.reply.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ReplyServiceImpl extends BaseServiceImpl<Reply, Long> implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	protected Mapper<Reply> getMapper() {
		return replyMapper;
	}

	@Override
	public void handleReply(Long id, ReplyCondition replyCondition) {
		Reply replySearch = replyMapper.selectByPrimaryKey(id);
		if (replySearch == null) {
			throw new BusinessException(ResultEnum.REPLY_NOT_EXIST);
		}
		if (StatusEnum.IsHandled.equals(replySearch.getStatus())) {
			throw new BusinessException(ResultEnum.REPLY_IS_HANDLE);
		}
		if (!UserContext.getCurrentUser().getDepartmentId().equals(replySearch.getDepartmentId())) {
			throw new BusinessException(ResultEnum.REPLY_IS_HANDLE);
		}
		Reply replyToUpdate = new Reply();
		replyToUpdate.setId(id);
		replyToUpdate.setStatus(StatusEnum.IsHandled);
		replyToUpdate.setUserId(UserContext.getCurrentUser().getId());
		replyToUpdate.setEndAt(replyCondition.getEndAt());
		replyToUpdate.setReturnAt(replyCondition.getReturnAt());
		replyToUpdate.setEvaluate(replyCondition.getEvaluate());
		replyToUpdate.setMethod(replyCondition.getMethod());
		replyToUpdate.setOverTime(replyCondition.getOverTime());
		replyToUpdate.setReason(replyCondition.getReason());
		replyToUpdate.setReplyContent(replyCondition.getReplyContent());
		replyToUpdate.setReturnContent(replyCondition.getReturnContent());
		replyToUpdate.setUrgeTimes(replyCondition.getUrgeTimes());
		replyMapper.updateByPrimaryKeySelective(replyToUpdate);
	}

	@Override
	public ReplyDTO getReplyDetail(Long id) {
		return replyMapper.getReplyDetail(id);
	}

}
