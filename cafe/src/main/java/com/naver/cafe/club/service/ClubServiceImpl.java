package com.naver.cafe.club.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naver.cafe.club.biz.ClubBiz;
import com.naver.cafe.club.vo.ClubListVO;
import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;
import com.naver.cafe.menu.biz.MenuBiz;
import com.naver.cafe.menu.vo.MenuSearchVO;
import com.naver.cafe.reply.biz.ReplyBiz;
import com.naver.cafe.reply.vo.ReplyVO;

public class ClubServiceImpl implements ClubService{
	// 다른 service가 사용하는 경우 private 멤버로만 사용하면 되기 때문에 service, web 패키지 작성 불필요 
	private MenuBiz menuBiz;
	private ClubBiz clubBiz;
	private ReplyBiz replyBiz;
	private Logger logger = LoggerFactory.getLogger(ClubServiceImpl.class);
	
	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
	}
	
	public void setClubBiz(ClubBiz clubBiz) {
		this.clubBiz = clubBiz;
	}
	
	public void setReplyBiz(ReplyBiz replyBiz) {
		this.replyBiz = replyBiz;
	}
	
	@Override
	public ClubListVO getAllClub(ClubSearchVO clubSearchVO) {
		
		ClubListVO clubListVO = clubBiz.getAllClub(clubSearchVO);
		
		// 사용자 권한에 따라 보여줄 카테고리 설정
		MenuSearchVO menuSearchVO = new MenuSearchVO();
		//menuSearchVO.setAuth("USR");
		clubListVO.setMenuList(menuBiz.getAllMenu(menuSearchVO));
		
		// 현재 메뉴 아이디 계층화해 가져오기 위한 과정
		menuSearchVO.setMenuId(clubSearchVO.getMenuId());
		clubListVO.setCurrentMenu( menuBiz.getAllMenu(menuSearchVO) );
		
		return clubListVO;
	}
	
	@Override
	public boolean addReadCount(ClubSearchVO clubSearchVO) {
		return clubBiz.addReadCount(clubSearchVO);
	}
	
	@Override
	public Map<String, Object> getOneClub(ClubSearchVO clubSearchVO) {
		// 게시글 정보 가져옴
		ClubVO clubVO = clubBiz.getOneClub(clubSearchVO);
		// 댓글 정보 가져와 clubVO에 넣음
		clubVO.setReplyVO(replyBiz.getAllReplies(clubSearchVO.getArticleId()));
		logger.info("replyListSize : " + clubVO.getReplyVO().size());
		
		Map<String, Object> club = new HashMap<String, Object>();
		// 게시글, 댓글 정보
		club.put("club", clubVO);
		
		// 권한에 따라 볼 수 있는 메뉴 리스트
		MenuSearchVO menuSearchVO = new MenuSearchVO();
		//TODO Admin 페이지 만들면 고쳐야 함
		menuSearchVO.setAuth("USR");
		club.put("menu",  menuBiz.getAllMenu(menuSearchVO));
		
		// 현재 메뉴 정보
		menuSearchVO.setMenuId(clubSearchVO.getMenuId());
		club.put("currentMenu",  menuBiz.getAllMenu(menuSearchVO));
		
		return club;
	}
	
	@Override
	public boolean addNewClub(ClubVO club) {
		return clubBiz.addNewClub(club);
	}

	@Override
	public boolean editOneClub(ClubVO club) {
		return clubBiz.editOneClub(club);
	}

	@Override
	public boolean removeOneClub(String clubId) {
		return clubBiz.removeOneClub(clubId);
	}

}
