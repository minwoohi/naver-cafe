package com.naver.cafe.reply.biz;

import java.util.List;

import com.naver.cafe.reply.dao.ReplyDao;
import com.naver.cafe.reply.vo.ReplyVO;

public class ReplyBizImpl implements ReplyBiz{
	
	private ReplyDao replyDao;
	
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	@Override
	public List<ReplyVO> getAllReplies(String articleId) {
		return replyDao.selectAllReplies(articleId);
	}

	@Override
	public boolean addOneReply(ReplyVO reply) {
		System.out.println("BizImpl");
		return replyDao.insertOneReply(reply) > 0;
	}

	@Override
	public boolean modifyOneReply(ReplyVO reply) {
		return replyDao.updateOneReply(reply) > 0;
	}

	@Override
	public boolean removeOneReply(String replyId) {
		return replyDao.deleteOneReply(replyId) > 0;
	}

}
