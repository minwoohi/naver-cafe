package com.naver.cafe.member.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.naver.cafe.member.vo.MemberVO;

public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Override
	public MemberVO selectOneMember(MemberVO member) {
		return getSqlSession().selectOne(NS + ".selectOneMember", member);
	}

	@Override
	public int selectLockStatus(String memberId) {
		return getSqlSession().selectOne(NS + ".selectLockStatus", memberId);
	}

	@Override
	public int plusLoginFailCount(String memberId) {
		return getSqlSession().update(NS + ".plusLoginFailCount", memberId);
	}

	@Override
	public int updateLockStatus(String memberId) {
		return getSqlSession().update(NS + ".updateLockStatus", memberId);
	}

	@Override
	public int updateClearLoginFailCount(String memberId) {
		return getSqlSession().update(NS + ".updateClearLoginFailCount", memberId);
	}

	@Override
	public int updateClearLockCount(String memberId) {
		return getSqlSession().update(NS + ".updateClearLockCount", memberId);
	}
	
	@Override
	public int insertOneMember(MemberVO member) {
		return getSqlSession().insert(NS+".insertOneMember", member);
	}
	
	@Override
	public String getSaltByMemberId(String memberId) {
		return getSqlSession().selectOne(NS + ".getSaltByMemberId", memberId);
	}
	
	@Override
	public int selectDuplicatedMemberId(String memberId) {
		return getSqlSession().selectOne(NS + ".selectDuplicatedMemberId", memberId);
	}

	@Override
	public int updateOneMember(MemberVO member) {
		return getSqlSession().update(NS + ".updateOneMember", member);
	}

	@Override
	public int deleteOneMember(String memberId) {
		return getSqlSession().update(NS + ".deleteOneMember", memberId);
	}

}
