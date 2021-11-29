package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import util.DBMS;

@WebServlet("/member/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 회원가입시 입력한 값을 꺼냄
		String id = request.getParameter("id");
		String pw1 = request.getParameter("pw1");
		String pw2 = request.getParameter("pw2");
		String email = request.getParameter("email");
		String phoneFirst = request.getParameter("phoneFirst");
		String phoneEtc = request.getParameter("phoneEtc");
		String job = request.getParameter("job");
		
		//...
		// 입력값 검증과 그에 따른 오류 상태코드 Response
		//...
		
		// DB에 접속
		Connection conn = DBMS.getConnection();
		
		//아이디가 중복되었을 때는 예외가 발생함
		//예외가 발생한다는게 서버에 굉장히 무리를 주는 동작
		//서버에 부하를 최소한으로 줄일 수 있는 방법은
		//가입하기 전에 해당 아이디로 조회해서
		//중보고디지 않은 아이디만 가입 되도록 처리해야함
		String sql = "SELECT * FROM memberinfo Where id=?";
		try {
		PreparedStatement selectPstmt = conn.prepareStatement(sql);
		selectPstmt.setString(1, id);
		
		ResultSet rs = selectPstmt.executeQuery();
		if(rs.next()) {
			//이미 사용중인 아이디라면
			// 사용중입니다 처리를 상황에 맞게 구현
			rs.close();
			selectPstmt.close();
			conn.close();
			
			return;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		// 회원가입 기능 구현
		// 회원가입 -> 사용자가 입력한 정보를 테이블에 저장
		
		//회원가입 쿼리 작성
		
		//회원가입 쿼리 실행
		
		
		try {
			sql = "INSERT INTO memberinfo(id,pw,email,phone,job,joinDate,loginDate) VALUES(?,?,?,?,?,?,?) ";
			String phone = phoneFirst + "-" + phoneEtc;
			
			LocalDateTime joinDate = LocalDateTime.now();
			LocalDateTime loginDate = LocalDateTime.now();
			
			// 날짜형식 yyyy-MM-dd HH:mm:ss
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw1);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			System.out.println("job = "+job);
			System.out.println("job length = "+job.length());
			pstmt.setString(5, job);
			pstmt.setString(6,dtf.format(joinDate));
			pstmt.setString(7, dtf.format(loginDate));			
			
			int result = pstmt.executeUpdate();
			
			// 회원가입 성공 여부 판단
			if(result == 1) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			pstmt.close();
			conn.close();
			//DBMS와 관련된 자원 해제
		} catch(SQLIntegrityConstraintViolationException e) {
			// 아이디가 중복되었을 때 이런 예외가 발생하므로 해당 샇랑에 대한 코드
			System.out.println("아이디가 중복되었을 때 처리하는 코드");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
