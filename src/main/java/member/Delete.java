package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBMS;

@WebServlet("/member/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 탈퇴 -> 회원 정보를 삭제
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");
		//DBMS에 접속
		Connection conn = DBMS.getConnection();
		
		//...
		//클라이언트가 정달해주는 id와 pw를 검증하는 코드
		//...
		try {
		//쿼리를 작성
		String sql ="DELETE FROM memberinfo WHERE id=? AND pw=?";
		
		
	
		//쿼리를 실행
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		
		if(pstmt.executeUpdate()==1)
		{	//회원탈퇴 완료 처리 및 응답
		}else {
			// 회원 탈퇴 실패 처리 및 응답
		}
		pstmt.close();
		conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		// 결과에 따라서 적절한 응답	
		
		
	}

}
