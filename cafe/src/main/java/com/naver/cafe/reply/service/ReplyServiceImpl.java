package com.naver.cafe.reply.service;

import com.naver.cafe.club.biz.ClubBiz;
import com.naver.cafe.reply.biz.ReplyBiz;
import com.naver.cafe.reply.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	private ReplyBiz replyBiz;
	private ClubBiz clubBiz;
	
	public void setClubBiz(ClubBiz clubBiz) {
		this.clubBiz = clubBiz;
	}
	
	public void setReplyBiz(ReplyBiz replyBiz) {
		this.replyBiz = replyBiz;
	}
	
	@Override
	public boolean addOneReply(ReplyVO reply) {
		return replyBiz.addOneReply(reply);
	}

	@Override
	public boolean modifyOneReply(ReplyVO reply) {
		return replyBiz.modifyOneReply(reply);
	}

	@Override
	public boolean removeOneReply(String replyId) {
		return replyBiz.removeOneReply(replyId);
	}



}
