package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.getBoardCont(no);

		request.setAttribute("Dto", dto);

		return "board_update.jsp";
	}

}
