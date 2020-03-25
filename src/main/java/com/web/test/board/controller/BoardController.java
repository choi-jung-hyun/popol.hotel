package com.web.test.board.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.test.board.service.BoardService;
import com.web.test.board.vo.BoardVO;
import com.web.test.file.util.FileUtil;
import com.web.test.member.vo.MemberVO;


@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	FileUtil fileUtile;
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/boardMain.do")
	public ModelAndView boardMain(BoardVO vo) throws Exception{
		System.out.println(vo.getSearchKey());
		System.out.println(vo.getSearchWrd());
		vo.setFirstIndex((vo.getPageIndex() - 1) * vo.getPageUnit());//  (1-1) * 10 = 0?
	    vo.setRecordCountPerPage(vo.getPageUnit());//한페이지에 몇개의 로우를 보여줄지
	    
		java.util.List<BoardVO> list = boardService.boardList(vo);
		int listCnt = boardService.boardListCnt(vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("pageSize", (listCnt -1) / vo.getPageUnit() +1);
		mav.addObject("vo", vo);
		
		mav.setViewName("/board/boardMain");
		
		return mav;
	}
	
	@RequestMapping("/goWrite.do")
	public String goWrite() {
		
		return "/board/write";
	}
	
	@ResponseBody
	@RequestMapping("/writeReg.do")
	public Map<String, Object> writeReg(BoardVO vo,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//세션에있는 로그인 정보
		MemberVO user = (MemberVO)request.getSession().getAttribute("loginId");
		vo.setUse_yn("Y");
		vo.setUser_email(user.getUserEmail());
		
		int result = boardService.boardReg(vo);
		
		if(result > 0) {
			map.put("resultCode", "1");
			map.put("msg", "게시물이 등록되었습니다.");
		}else {
			map.put("resultCode", "-1");
			map.put("msg", "게시물 등록에 실패했습니다.");
		}
		
		return map;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView boardView(BoardVO vo, HttpServletResponse response, HttpServletRequest request , HttpSession sesssion)throws Exception {
		ModelAndView mav = new ModelAndView();
		BoardVO result = boardService.boardView(vo);
		
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("cookie"+result.getBoard_no())) {
					System.out.println("쿠키 생성");
					viewCookie = cookies[i];
				}
			}
		}
		
		if(result != null) {
			if(viewCookie == null) {
				Cookie newCookie = new Cookie("cookie"+result.getBoard_no(), "|" + result.getBoard_no() + "|");
				
				response.addCookie(newCookie);
				int view_countUp = boardService.viewUp(result.getBoard_no());
				
				if(view_countUp > 0) {
					System.out.println("조회수 증가");
				}else {
					System.out.println("조회수 증가 실패");
				}
			}else {
				System.out.println("쿠키있음 조회수 증가x");
			}
		}
		
		mav.addObject("result", result);
		mav.addObject("vo",vo);
		
		mav.setViewName("/board/view");
		return mav;
	}
	@RequestMapping("/goBoardUpdate.do")
	public ModelAndView goBoardModify(BoardVO vo)throws Exception{
		ModelAndView mav = new ModelAndView();
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		mav.addObject("vo", vo);
		mav.setViewName("/board/write");
		
		return mav;
	}
	@ResponseBody
	@RequestMapping("/boardUpdate.do")
	public Map<String, Object> boardUpdate(BoardVO vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = boardService.boardUpdate(vo);
		
		if(result > 0) {
			map.put("resultCode", 1);
			map.put("msg", "게시물이 수정되었습니다.");
		}else {
			map.put("resultCode", -1);
			map.put("msg", "게시물 수정에 실패했습니다.");
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/boardDelete.do")
	public Map<String, Object> boardDelete(BoardVO vo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = boardService.boardDelete(vo);
		
		if(result > 0) {
			map.put("resultCode", 1);
			map.put("msg", "게시물이 삭제되었습니다.");
		}else {
			map.put("resultCode", -1);
			map.put("msg", " 게시물 삭제에 실패했습니다.");
		}
		
		return map;
	}
}

