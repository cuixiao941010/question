package com.cx.question.reply.repository;

import com.cx.question.entity.reply.Reply;
import com.cx.question.reply.model.ReplyDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ReplyMapper extends Mapper<Reply> {
    ReplyDTO getReplyDetail(@Param("id") Long id);

}
