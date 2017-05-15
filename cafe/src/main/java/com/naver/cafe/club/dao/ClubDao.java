package com.naver.cafe.club.dao;

import java.util.List;

import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;

public interface ClubDao {
	// namespace 상수로 지정해 오타 방지
	public static final String NS = "ClubDao";
	
	public int selectAllClubCount(ClubSearchVO clubSearchVO);
	
	public List<ClubVO> selectAllClub(ClubSearchVO clubSearchVO);
	
	public int updateReadCount(ClubSearchVO clubSearchVO);
	
	public ClubVO selectOneClub(ClubSearchVO clubSearchVO);
		
	public int insertNewClub(ClubVO club);
	
	public int updateOneClub(ClubVO club);
	
	public int deleteOneClub(String clubId);
		
}
