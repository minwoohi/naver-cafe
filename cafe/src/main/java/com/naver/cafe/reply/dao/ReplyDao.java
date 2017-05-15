package com.naver.cafe.reply.dao;

import java.util.List;

import com.naver.cafe.reply.vo.ReplyVO;

public interface ReplyDao {
	
	public static final String NS = "ReplyDao";
	
	public List<ReplyVO> selectAllReplies(String articleId);
	
	public int insertOneReply(ReplyVO reply);
	
	public int updateOneReply(ReplyVO reply);
	
	public int deleteOneReply(String replyId);

}
