package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 5679718683735199043L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// getRequestURI() : "/프로젝트명/파일명(*.do)라는 문자열을 반환하는 메서드
		String uri = request.getRequestURI();
		System.out.println("URI : " + uri);

		// getContextPath() : 현재 프로젝트명을 문자열로 반환하는 메서드
		String path = request.getContextPath();
		System.out.println("path : " + path);

		String command = uri.substring(path.length() + 1);
		System.out.println("command : " + command);

		Action action = null;

		if (command.equals("board_list.do")) {
			action = new BoardListAction();
		}else if(command.equals("board_cont.do")) {
			action = new BoardContAction();
		}else if(command.equals("board_write.do")) {
			action = new BoardWriteAction();
		}else if(command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
		}else if(command.equals("board_update_ok.do")) {
			action = new BoardUpdateOkAction();
		}else if(command.equals("board_delete.do")) {
			action = new BoardDeleteAction();
		}else if(command.equals("board_delete_ok.do")) {
			action = new BoardDeleteOkAction();
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
		}

		String path1 = action.execute(request, response);

		RequestDispatcher rd = request.getRequestDispatcher(path1);
		rd.forward(request, response);

	}
}
