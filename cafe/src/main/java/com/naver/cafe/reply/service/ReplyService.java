package com.naver.cafe.reply.service;

import com.naver.cafe.reply.vo.ReplyVO;

public interface ReplyService {
	public boolean addOneReply(ReplyVO reply);

	public boolean modifyOneReply(ReplyVO reply);

	public boolean removeOneReply(String replyId);
}
