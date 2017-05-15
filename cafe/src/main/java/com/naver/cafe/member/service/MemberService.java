package com.naver.cafe.member.service;

import javax.servlet.http.HttpSession;

import com.naver.cafe.member.vo.MemberVO;

public interface MemberService {
	public MemberVO getOneMember(MemberVO member);
	
	public String getSaltByMemberId(String memberId);
	
	public boolean checkDuplicatedMemberId(String memberId);
	
	public boolean addOneMember(MemberVO member);
	
	public boolean login(HttpSession session, MemberVO member);
	
	public boolean modifyOneMember(MemberVO member);
	
	public boolean removeOneMember(String memberId);
	
}
