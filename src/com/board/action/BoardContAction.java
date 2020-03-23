package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 글제목을 클릭 시 상세내용을 보여주는 클래스
		
		int board_no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO cont = dao.getBoardCont(board_no);
		request.setAttribute("Cont", cont);

		return "board_cont.jsp";
	}

}
