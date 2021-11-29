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
		//�Է°� ������ �׿� ���� ���� ���� �ڵ� Response
		//
		
		//DB�� ����
		
		try {
			Connection conn = DBMS.getConnection();
			// �α��� -> ����ڰ� �Է��� ���̵�� ��й�ȣ�� ��ġ�ϴ� ������ �ִ��� ��ȸ�ϴ� ���
			
			//�����?ȸ�� ? ���̺��� ���̵�� ��й�ȣ�� �������� ����ؼ� ��ġ�ϴ� ����9���ڵ�)�� �ִ��� ��ȸ
			String sql = "SELECT * FROM memberinfo WHERE id =? AND pw =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			// ��ȸ ����� ������ �α��� ����// ��ȸ ����� ������ �α��� ����
			boolean isLogin = rs.next();
			if(isLogin) {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", true);
				session.setAttribute("id", id);
				session.setMaxInactiveInterval(600);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				PrintWriter out = response.getWriter();
				// �߸��Է��ߴ� �� �˷��ִ� HTML �ڵ带 ����
			}
			
			// DBMS�� ���õ� �ڿ� ����
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("DBMS�� ���õ� ������ ������");
			e.printStackTrace();
		}
		
	}

}
