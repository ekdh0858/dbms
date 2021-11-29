package chapter1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbms/ex1")
public class DBMSEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����̹��� �ε��Ϸ��� Class.forName �޼��带 ����ؾ���
				try {
					
					Class.forName("org.mariadb.jdbc.Driver");
					
					System.out.println("DBMS ���� ��");
				// JDBC Driver �� ����ؼ� DBMS�� ����
					Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=0000");
					
					//employees ���̺��� ����� 20000�� �̻��� ��� 5�� ��ȸ
//					Statement stmt = connection.createStatement();
					// ������ ��Ȳ�� ���� �ٲ�� ��찡 ���� ������ ���ڿ��� ���� ������ �����ϸ�
					// ���߿� ������ �����ؾ� �� �� ��� ������ �����ؾ� �ϴ��� �Ѵ��� ã�� ����
					
//					String sql = "SELECT * FROM employees WHERE emp_no >= 20000 LIMIT 5";
					
//					ResultSet rs =stmt.executeQuery(sql);
					// executeUpdate �� int���·� ���� ��ȯ
					String sql = "SELECT * FROM employees WHERE emp_no >= ? LIMIT ?";
					
					// PreparedStatement�� ������ ���� �ٲ�� ��Ȳ���� � ������ �ٲ�� �ϴ��� ���� �� �� ����
					// ������ �̸� �������ϴ��� ���ϴ����� ū ���̰� ����
					// ������ �������Ѵ� -> ������ ����� ���� ������ ������ Ÿ������ �ٲ۴�
					PreparedStatement pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, 20000);
					pstmt.setInt(2, 5);
					
					
					ResultSet rs = pstmt.executeQuery();
					
					int count =1;
					while(rs.next()) {
						int emp_no = rs.getInt("emp_no");
						String birth_date = rs.getString("birth_date");
						String first_name = rs.getString("first_name");
						String last_name = rs.getString("last_name");
						char gender = rs.getString("gender").charAt(0);
						String hire_date = rs.getString("hire_date");
						
						System.out.println("<<"+count+"��° ����>>");
						System.out.println("emp_no="+emp_no);
						System.out.println("first_name="+first_name);
						System.out.println("last_name="+last_name);
						System.out.println("birth_date="+birth_date);
						System.out.println("gender="+gender);
						System.out.println("hire_date="+hire_date);
						// get�޼��� ����Ҷ� index�ε� ��밡�� rs.getString(1);ó��
						count++;
					}
					rs.close();
//					stmt.close();
					pstmt.close();
					connection.close();
					//
					
					System.out.println("DBMS ���� ����");
				} catch (ClassNotFoundException e) {
				//	e.printStackTrace();
					System.out.println("����̹� �ε� ����");
				} catch (SQLException e) {
//					e.printStackTrace();
					System.out.println("DBMS ���� ����");
				}
				}


}
