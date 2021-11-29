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

/**
 * Servlet implementation class Update
 */
@WebServlet("/memeber/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // GET -> 정보를 조회할 때 사용하는 Method
	// 정보 수정 페이지에 접근했을 때 사용자의 정보를 갖고 있는 페이지를 보여주는 역활
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		try {
			Connection conn = DBMS.getConnection();
			
			String sql = "SELECT * FROM memberinfo WHERE id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
					
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pw =rs.getString("pw");
				String email =rs.getString("email");
				String phone =rs.getString("phone");
				String job =rs.getString("job");
				
				// 정보를 수정할 회원 정보를 찾았다면 
				
				// 1. 회원정보 페이지로 forward : 현재 우리는 JSP를 배우지 않았으니까 
				// 정적인 페이지인 HTML에 조회한 사용자의 정보를 보여줄수있도록 코딩을 할 수 없음
				
				// 자바스크립트의 ajax를 사용하면 해결할 수 있음, forward가 아니라 조회한 정보를
				// JSON데이터 타입으로 담아서 ajax가 받아서 처리하도록 함
				String memberinfo = "{\"id\":"+id+",\"pw\": "+ pw+",\"email\": "+ email+",\"phone\": "+ phone+",\"job\": "+ job+ "}";
				response.setContentType("application/json;charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				out.print(memberinfo);
				// 2. 직접 페이지 출력
//				PrintWriter out = response.getWriter();
//				out.print("<html>");
//				out.print(".....");
//				out.print("</html>");
			}else {
				//아이디로 사용자의 정보를 수정할 회원 정보를 찾지 못했다면
				// (예, 회원탈퇴를 한 다음 정보 수정 페이지로 왔을 경우,...)
			}
			
		}catch(SQLException e) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 전달한 파라밑터 값을 꺼낸다
		
		// 전달 값 검증
		
		// UPDATE 쿼리를 사용해서 정보 갱신
		
		// 갱신 결과를 따라서 적절한 처리
	}

}
