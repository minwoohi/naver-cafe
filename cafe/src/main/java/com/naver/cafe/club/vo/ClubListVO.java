package com.naver.cafe.club.vo;

import java.util.List;

import com.naver.cafe.common.web.Pager;
import com.naver.cafe.common.web.PagerFactory;
import com.naver.cafe.menu.vo.MenuVO;

	// 게시판 객체
public class ClubListVO {
	
	// MyBatis에서 abstract 클래스는 private 멤버로 사용할 수 없다. 따라서 Pager 객체를 다른 클래스에서 객체화 시켜 
	// 관리해줘야 함
	
	private List<ClubVO> clubList;
	private List<MenuVO> menuList;
	private List<MenuVO> currentMenu;
	
	private Pager pager;
	
	public List<ClubVO> getClubList() {
		return clubList;
	}
	public void setClubList(List<ClubVO> clubList) {
		this.clubList = clubList;
	}
	public List<MenuVO> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuVO> menuList) {
		this.menuList = menuList;
	}
	public Pager getPager() {
		if(pager == null){
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public List<MenuVO> getCurrentMenu() {
		return currentMenu;
	}
	public void setCurrentMenu(List<MenuVO> currentMenu) {
		this.currentMenu = currentMenu;
	}
	
	
}
