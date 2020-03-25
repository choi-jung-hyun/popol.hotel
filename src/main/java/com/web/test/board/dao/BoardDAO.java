package com.web.test.board.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.test.board.vo.BoardVO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int boardReg(BoardVO vo) throws Exception {

		return sqlSession.insert("BoardDAO.boardReg", vo);
	}
	
	public java.util.List<BoardVO> boardList(BoardVO vo) throws Exception {
		return sqlSession.selectList("BoardDAO.boardList", vo);
	}
	
	public int boardListCnt(BoardVO vo)throws Exception {
		return sqlSession.selectOne("BoardDAO.boardListCnt", vo);
		
	}
	
	public BoardVO boardView(BoardVO vo) throws Exception{
		return sqlSession.selectOne("BoardDAO.boardView", vo);
	}
	
	public int boardUpdate(BoardVO vo) throws Exception{
		return sqlSession.update("BoardDAO.boardUpdate", vo);
	}
	
	public int viewUp(String board_no) throws Exception{
		return sqlSession.update("BoardDAO.viewUp", board_no);
	}
	
	public int boardDelete(BoardVO vo) throws Exception{
		return sqlSession.delete("BoardDAO.boardDelete", vo);
	}
}
