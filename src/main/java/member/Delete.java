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
		//ȸ�� Ż�� -> ȸ�� ������ ����
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");
		//DBMS�� ����
		Connection conn = DBMS.getConnection();
		
		//...
		//Ŭ���̾�Ʈ�� �������ִ� id�� pw�� �����ϴ� �ڵ�
		//...
		try {
		//������ �ۼ�
		String sql ="DELETE FROM memberinfo WHERE id=? AND pw=?";
		
		
	
		//������ ����
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		
		if(pstmt.executeUpdate()==1)
		{	//ȸ��Ż�� �Ϸ� ó�� �� ����
		}else {
			// ȸ�� Ż�� ���� ó�� �� ����
		}
		pstmt.close();
		conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		// ����� ���� ������ ����	
		
		
	}

}
