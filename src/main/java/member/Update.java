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
    // GET -> ������ ��ȸ�� �� ����ϴ� Method
	// ���� ���� �������� �������� �� ������� ������ ���� �ִ� �������� �����ִ� ��Ȱ
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
				
				// ������ ������ ȸ�� ������ ã�Ҵٸ� 
				
				// 1. ȸ������ �������� forward : ���� �츮�� JSP�� ����� �ʾ����ϱ� 
				// ������ �������� HTML�� ��ȸ�� ������� ������ �����ټ��ֵ��� �ڵ��� �� �� ����
				
				// �ڹٽ�ũ��Ʈ�� ajax�� ����ϸ� �ذ��� �� ����, forward�� �ƴ϶� ��ȸ�� ������
				// JSON������ Ÿ������ ��Ƽ� ajax�� �޾Ƽ� ó���ϵ��� ��
				String memberinfo = "{\"id\":"+id+",\"pw\": "+ pw+",\"email\": "+ email+",\"phone\": "+ phone+",\"job\": "+ job+ "}";
				response.setContentType("application/json;charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				out.print(memberinfo);
				// 2. ���� ������ ���
//				PrintWriter out = response.getWriter();
//				out.print("<html>");
//				out.print(".....");
//				out.print("</html>");
			}else {
				//���̵�� ������� ������ ������ ȸ�� ������ ã�� ���ߴٸ�
				// (��, ȸ��Ż�� �� ���� ���� ���� �������� ���� ���,...)
			}
			
		}catch(SQLException e) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ŭ���̾�Ʈ�� ������ �Ķ���� ���� ������
		
		// ���� �� ����
		
		// UPDATE ������ ����ؼ� ���� ����
		
		// ���� ����� ���� ������ ó��
	}

}
