package com.naver.cafe.member.dao;

import com.naver.cafe.member.vo.MemberVO;

public interface MemberDao {
	
	public static final String NS = "MemberDao";
	
	public int selectLockStatus(String memberId);
	
	public int plusLoginFailCount(String memberId);
	
	public int updateLockStatus(String memberId);
	
	public int updateClearLoginFailCount(String memberId);
	
	public int updateClearLockCount(String memberId);
	
	public MemberVO selectOneMember(MemberVO member);
	
	public String getSaltByMemberId(String memberId);
	
	public int selectDuplicatedMemberId(String memberId);
	
	public int insertOneMember(MemberVO member);
	
	public int updateOneMember(MemberVO member);
	
	public int deleteOneMember(String memberId);
}
