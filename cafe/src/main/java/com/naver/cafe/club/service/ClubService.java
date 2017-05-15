package com.naver.cafe.club.service;

import java.util.Map;

import com.naver.cafe.club.vo.ClubListVO;
import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;
import com.naver.cafe.reply.vo.ReplyVO;

public interface ClubService {
	public ClubListVO getAllClub(ClubSearchVO clubSearchVO);
	
	public boolean addReadCount(ClubSearchVO clubSearchVO);
	
	public Map<String, Object> getOneClub(ClubSearchVO clubSearchVO);
	
	public boolean addNewClub(ClubVO club);
	
	public boolean editOneClub(ClubVO club);
	
	public boolean removeOneClub(String clubId);
}
