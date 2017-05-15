package com.naver.cafe.member.biz;

import com.naver.cafe.member.vo.MemberVO;

public interface MemberBiz {
	public MemberVO getOneMember(MemberVO member);

	public int selectLockStatus(String memberId);

	public int plusLoginFailCount(String memberId);

	public int updateLockStatus(String memberId);

	public int updateClearLoginFailCount(String memberId);

	public int updateClearLockCount(String memberId);

	public String getSaltByMemberId(String memberId);

	public boolean checkDuplicatedMemberId(String memberId);
	
	public boolean mofidyOneMember(MemberVO member);
	
	public boolean removeOneMember(String memberId);

	public boolean addOneMember(MemberVO member);
}
