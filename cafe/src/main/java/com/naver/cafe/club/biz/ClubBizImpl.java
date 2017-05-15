package com.naver.cafe.club.biz;

import com.naver.cafe.club.dao.ClubDao;
import com.naver.cafe.club.vo.ClubListVO;
import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;

public class ClubBizImpl implements ClubBiz {
	private ClubDao clubDao;
	
	public void setClubDao(ClubDao clubDao) {
		this.clubDao = clubDao;
	}

	@Override
	public ClubListVO getAllClub(ClubSearchVO clubSearchVO) {
		int totalCount = clubDao.selectAllClubCount(clubSearchVO);
		
		ClubListVO clubListVO = new ClubListVO();
		// TODO Pager에 총 갯수 저장하기
		clubListVO.getPager().setTotalArticleCount(totalCount);
		clubListVO.getPager().setPageNumber(clubSearchVO.getPageNo());
		
		clubSearchVO.setEndArticleNumber(clubListVO.getPager().getEndArticleNumber());
		clubSearchVO.setStartArticleNumber(clubListVO.getPager().getStartArticleNumber());
		
		if(totalCount > 0){
			clubListVO.setClubList(clubDao.selectAllClub(clubSearchVO));
		}
		
		return clubListVO;
		
	}
	
	@Override
	public boolean addReadCount(ClubSearchVO clubSearchVO) {
		return clubDao.updateReadCount(clubSearchVO) > 0;
	}
	
	@Override
	public ClubVO getOneClub(ClubSearchVO clubSearchVO) {
		return clubDao.selectOneClub(clubSearchVO);
	}
	
	@Override
	public boolean addNewClub(ClubVO club) {
		return clubDao.insertNewClub(club) > 0;
	}

	@Override
	public boolean editOneClub(ClubVO club) {
		return clubDao.updateOneClub(club) > 0;
	}

	@Override
	public boolean removeOneClub(String clubId) {
		return clubDao.deleteOneClub(clubId) > 0;
	}
	
}
