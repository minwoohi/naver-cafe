package com.naver.cafe.club.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.naver.cafe.club.vo.ClubSearchVO;
import com.naver.cafe.club.vo.ClubVO;

public class ClubDaoImpl extends SqlSessionDaoSupport implements ClubDao{

	@Override
	public int selectAllClubCount(ClubSearchVO clubSearchVO) {
		return getSqlSession().selectOne(NS +".selectAllClubCount", clubSearchVO);
	}

	@Override
	public List<ClubVO> selectAllClub(ClubSearchVO clubSearchVO) {
		return getSqlSession().selectList(NS + ".selectAllClub", clubSearchVO);
	}
	
	@Override
	public int updateReadCount(ClubSearchVO clubSearchVO) {
		return getSqlSession().update(NS + ".updateReadCount", clubSearchVO);
	}
	
	@Override
	public ClubVO selectOneClub(ClubSearchVO clubSearchVO) {
		return getSqlSession().selectOne(NS + ".selectOneClub", clubSearchVO);
	}
	
	@Override
	public int insertNewClub(ClubVO club) {
		return getSqlSession().insert(NS + ".insertNewClub", club);
	}

	@Override
	public int updateOneClub(ClubVO club) {
		return getSqlSession().update(NS + ".updateOneClub", club);
	}

	@Override
	public int deleteOneClub(String clubId) {
		return getSqlSession().delete(NS + ".deleteOneClub", clubId);
	}
}
