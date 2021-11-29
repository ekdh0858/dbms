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
		// ȸ�����Խ� �Է��� ���� ����
		String id = request.getParameter("id");
		String pw1 = request.getParameter("pw1");
		String pw2 = request.getParameter("pw2");
		String email = request.getParameter("email");
		String phoneFirst = request.getParameter("phoneFirst");
		String phoneEtc = request.getParameter("phoneEtc");
		String job = request.getParameter("job");
		
		//...
		// �Է°� ������ �׿� ���� ���� �����ڵ� Response
		//...
		
		// DB�� ����
		Connection conn = DBMS.getConnection();
		
		//���̵� �ߺ��Ǿ��� ���� ���ܰ� �߻���
		//���ܰ� �߻��Ѵٴ°� ������ ������ ������ �ִ� ����
		//������ ���ϸ� �ּ������� ���� �� �ִ� �����
		//�����ϱ� ���� �ش� ���̵�� ��ȸ�ؼ�
		//�ߺ������ ���� ���̵� ���� �ǵ��� ó���ؾ���
		String sql = "SELECT * FROM memberinfo Where id=?";
		try {
		PreparedStatement selectPstmt = conn.prepareStatement(sql);
		selectPstmt.setString(1, id);
		
		ResultSet rs = selectPstmt.executeQuery();
		if(rs.next()) {
			//�̹� ������� ���̵���
			// ������Դϴ� ó���� ��Ȳ�� �°� ����
			rs.close();
			selectPstmt.close();
			conn.close();
			
			return;
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		// ȸ������ ��� ����
		// ȸ������ -> ����ڰ� �Է��� ������ ���̺� ����
		
		//ȸ������ ���� �ۼ�
		
		//ȸ������ ���� ����
		
		
		try {
			sql = "INSERT INTO memberinfo(id,pw,email,phone,job,joinDate,loginDate) VALUES(?,?,?,?,?,?,?) ";
			String phone = phoneFirst + "-" + phoneEtc;
			
			LocalDateTime joinDate = LocalDateTime.now();
			LocalDateTime loginDate = LocalDateTime.now();
			
			// ��¥���� yyyy-MM-dd HH:mm:ss
			
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
			
			// ȸ������ ���� ���� �Ǵ�
			if(result == 1) {
				response.setStatus(HttpServletResponse.SC_CREATED);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
			pstmt.close();
			conn.close();
			//DBMS�� ���õ� �ڿ� ����
		} catch(SQLIntegrityConstraintViolationException e) {
			// ���̵� �ߺ��Ǿ��� �� �̷� ���ܰ� �߻��ϹǷ� �ش� ������ ���� �ڵ�
			System.out.println("���̵� �ߺ��Ǿ��� �� ó���ϴ� �ڵ�");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
