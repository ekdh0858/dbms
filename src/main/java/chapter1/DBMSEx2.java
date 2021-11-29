package chapter1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/dbms/ex2")
public class DBMSEx2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					InitialContext ic = new InitialContext();
					
					DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/maria"); //커넥션 풀의 이름이 마지막임
					
					Connection connection = ds.getConnection();
					
					String sql = "SELECT * FROM employees WHERE emp_no >= ? LIMIT ?";
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
						
						System.out.println("<<"+count+"번째 정보>>");
						System.out.println("emp_no="+emp_no);
						System.out.println("first_name="+first_name);
						System.out.println("last_name="+last_name);
						System.out.println("birth_date="+birth_date);
						System.out.println("gender="+gender);
						System.out.println("hire_date="+hire_date);
						count++;
					}
					rs.close();
					pstmt.close();
					connection.close();
					
				} catch (SQLException e) {
					System.out.println("DBMS 접속 실패");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("Connection Pool 불러오기 실패");
				}
				}


}
