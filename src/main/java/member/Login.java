package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBMS;



@WebServlet("/member/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//
		//입력값 검증과 그에 따른 오류 상태 코드 Response
		//
		
		//DB에 접속
		
		try {
			Connection conn = DBMS.getConnection();
			// 로그인 -> 사용자가 입력한 아이디와 비밀번호가 일치하는 계정이 있는지 조회하는 기능
			
			//사용자?회원 ? 테이블에서 아이디와 비밀번호를 조건으로 사용해서 일치하는 정보9레코드)가 있는지 조회
			String sql = "SELECT * FROM memberinfo WHERE id =? AND pw =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			// 조회 결과가 있으면 로그인 성공// 조회 결과가 없으면 로그인 실패
			boolean isLogin = rs.next();
			if(isLogin) {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("id", id);
				session.setMaxInactiveInterval(600);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				PrintWriter out = response.getWriter();
				// 잘못입력했다 를 알려주는 HTML 코드를 응답
			}
			
			// DBMS와 관련된 자원 해제
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("DBMS와 관련되 문제가 생겼음");
			e.printStackTrace();
		}
		
	}

}
