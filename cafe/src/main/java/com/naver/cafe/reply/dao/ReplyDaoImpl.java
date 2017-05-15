package com.naver.cafe.reply.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.naver.cafe.reply.vo.ReplyVO;

public class ReplyDaoImpl extends SqlSessionDaoSupport implements ReplyDao {

	@Override
	public List<ReplyVO> selectAllReplies(String articleId) {
		return getSqlSession().selectList(NS + ".selectAllReplies", articleId);
	}

	@Override
	public int insertOneReply(ReplyVO reply) {
		System.out.println("DaoImpl");
		return getSqlSession().insert(NS + ".insertOneReply", reply);
	}

	@Override
	public int updateOneReply(ReplyVO reply) {
		return getSqlSession().update(NS + ".updateOneReply", reply);
	}
	
	@Override
	public int deleteOneReply(String replyId) {
		return getSqlSession().delete(NS + ".deleteOneReply", replyId);
	}
	
}
