package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String search_category = request.getParameter("search_category");
		String search_items = request.getParameter("search_items");

		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> search = dao.searchBoard(search_category, search_items);

		request.setAttribute("Search", search);
		return "board_search.jsp";
	}

}
