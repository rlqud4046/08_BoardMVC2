package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	/*
	 * 싱글톤 객체 만들기 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private로 선언한다. 2. 정적(static) 멤버로 선언을
	 * 한다.
	 */

	private static BoardDAO instance = new BoardDAO();

	/*
	 * 3. 기본생성자는 외부에서 접근이 되지 않도록 해야한다. 외부에서 new를 사용하지 못하게 하는 접근 기법
	 */

	private BoardDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 retrun 해 주는 getInstance() 메서드를 만들어 준다.
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	public Connection openConn() {

		try {
			// 1. JNDI객체 생성
			InitialContext ic = new InitialContext();

			// 2.lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	} // openConn() 메서드 end

	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			openConn();
			sql = "select * from board1 order by board_no desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));

				list.add(dto);
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	} // getBoardList() 메서드 end

	// board1 테이블의 글번호에 해당하는 글을 가져오는 메서드
	public BoardDTO getBoardCont(int no) {

		BoardDTO dto = new BoardDTO();
		try {
			openConn();
			// 조회수 증가
			sql = "update board1 set board_hit = board_hit+1 where board_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

			// 상세내역 보기
			sql = "select * from board1 where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dto;

	} // getBoardCont() 메서드의 end

	// board1 테이블에 게시글을 추가하는 메서드 (시퀀스를 안쓰는 방법으로 해본것)
	public int insertBoard(BoardDTO dto) {

		int result = 0, count = 0;

		try {
			openConn();
			con.setAutoCommit(false);
			sql = "select max(board_no) from board1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1) + 1;
			} else { // rs.next()가 없다 = 작성한 글이 없다 > 글번호를 1로 해준다
				count = 1;
			}

			sql = "insert into board1 values(?,?,?,?,?,default,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());

			result = pstmt.executeUpdate();
			con.commit(); // 완전히 DB에 저장하는 메서드

		} catch (SQLException e) {
			// 처리 도중에 문제가 발생한 경우 이전 상태로 되돌리는 메서드
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return result;
	}

	public int updateBoard(BoardDTO dto) {

		int result = 0;

		try {
			openConn();
			sql = "update board1 set board_title=?, board_cont=? where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return result;
	}

	public int deleteBoard(int board_no, String board_pwd) {
		int result = 0;

		try {
			openConn();
			sql = "select * from board1 where board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (board_pwd.equals(rs.getString("board_pwd"))) {

					sql = "delete from board1 where board_no=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, board_no);
					result = pstmt.executeUpdate();
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public List<BoardDTO> searchBoard(String search_category, String search_items) {
		List<BoardDTO> search = new ArrayList<BoardDTO>();

		try {
			openConn();
			if (search_category.equals("title")) {
				sql = "select * from board1 where board_title like ?";
			} else if (search_category.equals("cont")) {
				sql = "select * from board1 where board_cont like ?";
			} else if (search_category.equals("title_cont")) {
				sql = "select * from board1 where board_title like ? or board_cont like ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search_items + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_regdate(rs.getString("board_regdate"));
				search.add(dto);

			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return search;
	}

}
