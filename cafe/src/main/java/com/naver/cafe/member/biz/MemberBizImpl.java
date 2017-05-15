package com.naver.cafe.member.biz;

import com.naver.cafe.member.dao.MemberDao;
import com.naver.cafe.member.vo.MemberVO;

public class MemberBizImpl implements MemberBiz{

	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public MemberVO getOneMember(MemberVO member) {
		return memberDao.selectOneMember(member);
	}
	
	@Override
	public int selectLockStatus(String memberId) {
		return memberDao.selectLockStatus(memberId);
	}

	@Override
	public int plusLoginFailCount(String memberId) {
		return memberDao.plusLoginFailCount(memberId);
	}

	@Override
	public int updateLockStatus(String memberId) {
		return memberDao.updateLockStatus(memberId);
	}

	@Override
	public int updateClearLoginFailCount(String memberId) {
		return memberDao.updateClearLoginFailCount(memberId);
	}

	@Override
	public int updateClearLockCount(String memberId) {
		return memberDao.updateClearLockCount(memberId);
	}
	
	@Override
	public String getSaltByMemberId(String memberId) {
		return memberDao.getSaltByMemberId(memberId);
	}
	
	@Override
	public boolean checkDuplicatedMemberId(String memberId) {
		return memberDao.selectDuplicatedMemberId(memberId) > 0;
	}

	@Override
	public boolean addOneMember(MemberVO member) {
		return memberDao.insertOneMember(member) > 0;
	}

	@Override
	public boolean mofidyOneMember(MemberVO member) {
		return memberDao.updateOneMember(member) > 0;
	}

	@Override
	public boolean removeOneMember(String memberId) {
		return memberDao.deleteOneMember(memberId) > 0;
	}

}
