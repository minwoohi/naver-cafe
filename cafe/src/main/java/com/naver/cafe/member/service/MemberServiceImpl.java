package com.naver.cafe.member.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naver.cafe.common.SHA256Util;
import com.naver.cafe.member.biz.MemberBiz;
import com.naver.cafe.member.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberBiz memberBiz;
	//private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	public void setMemberBiz(MemberBiz memberBiz) {
		this.memberBiz = memberBiz;
	}

	@Override
	public MemberVO getOneMember(MemberVO member) {
		return memberBiz.getOneMember(member);
	}

	@Override
	public String getSaltByMemberId(String memberId) {
		return memberBiz.getSaltByMemberId(memberId);
	}

	@Override
	public boolean checkDuplicatedMemberId(String memberId) {
		return memberBiz.checkDuplicatedMemberId(memberId);
	}

	@Override
	public boolean addOneMember(MemberVO member) {
		return memberBiz.addOneMember(member);
	}

	@Override
	public boolean login(HttpSession session, MemberVO member) {
		
		if (memberBiz.selectLockStatus(member.getMemberId()) > 0) {
			return false;
		}
		
		String memberId = member.getMemberId();
		String salt = memberBiz.getSaltByMemberId(memberId);
		
		if(salt == null){ // 로그인 시도 가능하고 id 존재하지 않는 경우
			return false;
		}

		// 로그인 시도 가능하고 id 존재하는 경우
		memberBiz.updateClearLoginFailCount(member.getMemberId());
		
		String password = SHA256Util.getEncrypt(member.getMemberPassword(), salt);

		member.setMemberPassword(password);

		memberBiz.updateLockStatus(memberId);
		
		if (memberBiz.getOneMember(member) != null) {
			memberBiz.updateClearLockCount(member.getMemberId());
			session.setAttribute("_MEMBER_",  member);
			return true;
		} else {
			memberBiz.plusLoginFailCount(memberId);
			return false;
		}
	}

	@Override
	public boolean modifyOneMember(MemberVO member) {
		return memberBiz.mofidyOneMember(member);
	}

	@Override
	public boolean removeOneMember(String memberId) {
		return memberBiz.removeOneMember(memberId);
	}
}
