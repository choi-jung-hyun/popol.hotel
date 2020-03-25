package com.web.test.board.serviceImpl;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.test.board.dao.BoardDAO;
import com.web.test.board.service.BoardService;
import com.web.test.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public int boardReg(BoardVO vo)throws Exception{
		
		return boardDAO.boardReg(vo);
	}
	
	@Override
	public java.util.List<BoardVO> boardList(BoardVO vo)throws Exception{
		
		return boardDAO.boardList(vo);
	}
	
	@Override
	public int boardListCnt(BoardVO vo)throws Exception{
		
		return boardDAO.boardListCnt(vo);
	}
	
	@Override
	public BoardVO boardView(BoardVO vo)throws Exception{
		
		return boardDAO.boardView(vo);
	}
	
	@Override
	public int boardUpdate(BoardVO vo) throws Exception {
		
		return boardDAO.boardUpdate(vo);
	}
	
	@Override
	public int viewUp(String board_no) throws Exception{
		
		return boardDAO.viewUp(board_no);
	}
	
	@Override
	public int boardDelete(BoardVO vo) throws Exception{
		
		return boardDAO.boardDelete(vo);
	}
}
