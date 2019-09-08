package com.cx.question.reply.service;


import com.cx.question.base.service.BaseService;
import com.cx.question.entity.reply.Reply;
import com.cx.question.reply.model.ReplyCondition;
import com.cx.question.reply.model.ReplyDTO;

public interface ReplyService extends BaseService<Reply, Long> {

    void handleReply(Long id, ReplyCondition replyCondition);

    ReplyDTO getReplyDetail(Long id);
}
