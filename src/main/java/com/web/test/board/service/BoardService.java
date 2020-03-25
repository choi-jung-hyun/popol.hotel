package com.web.test.board.service;


import com.web.test.board.vo.BoardVO;

public interface BoardService {

	int boardReg(BoardVO vo)throws Exception;

	java.util.List<BoardVO> boardList(BoardVO vo)throws Exception;
	
	int boardListCnt(BoardVO vo)throws Exception;
	
	BoardVO boardView(BoardVO vo) throws Exception;
	
	int boardUpdate(BoardVO vo) throws Exception;
	
	int viewUp(String board_no) throws Exception;
	
	int boardDelete(BoardVO vo) throws Exception;
}
