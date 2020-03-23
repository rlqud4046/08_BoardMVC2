package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BoardDTO dto = new BoardDTO();
		
		String board_title = request.getParameter("board_title");
		String board_cont = request.getParameter("board_cont");
		
		dto.setBoard_no(board_no);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		BoardDAO dao = BoardDAO.getInstance();
		dao.updateBoard(dto);
		
		
		
		
		
		
		return "board_cont.do?no="+board_no;
	}

}
