package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stubg

		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String board_pwd = request.getParameter("board_pwd");

		BoardDAO dao = BoardDAO.getInstance();

		int res = dao.deleteBoard(board_no, board_pwd);
		PrintWriter out = response.getWriter();

		String addr = null;

		if (res > 0) {
			addr = "board_list.do";
		} else {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해주세요')");
			out.println("location.href='board_delete.do?no=" + board_no + "'");
			out.println("</script>");
			
		}

		return addr;
	}

}
