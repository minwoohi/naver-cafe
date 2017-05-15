package com.naver.cafe.reply.biz;

import java.util.List;

import com.naver.cafe.reply.vo.ReplyVO;

public interface ReplyBiz {
	public List<ReplyVO> getAllReplies(String articleId);
	
	public boolean addOneReply(ReplyVO reply);
	
	public boolean modifyOneReply(ReplyVO reply);
	
	public boolean removeOneReply(String replyId);
	
}
