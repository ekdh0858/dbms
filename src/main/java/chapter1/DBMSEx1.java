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
		// 드라이버를 로딩하려면 Class.forName 메서드를 사용해야함
				try {
					
					Class.forName("org.mariadb.jdbc.Driver");
					
					System.out.println("DBMS 접속 전");
				// JDBC Driver 를 사용해서 DBMS에 접속
					Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=0000");
					
					//employees 테이블에서 사번이 20000번 이상인 사람 5명 조회
//					Statement stmt = connection.createStatement();
					// 조건은 상황에 따라서 바뀌는 경우가 많기 때문에 문자열로 직접 조건을 구성하면
					// 나중에 조건을 수정해야 할 때 어느 조건을 수정해야 하는지 한눈에 찾기 힘듦
					
//					String sql = "SELECT * FROM employees WHERE emp_no >= 20000 LIMIT 5";
					
//					ResultSet rs =stmt.executeQuery(sql);
					// executeUpdate 는 int형태로 값을 반환
					String sql = "SELECT * FROM employees WHERE emp_no >= ? LIMIT ?";
					
					// PreparedStatement는 조건이 자주 바뀌는 상황에서 어떤 조건을 바꿔야 하는지 쉽게 알 수 있음
					// 쿼리를 미리 컴파일하느냐 안하느냐의 큰 차이가 있음
					// 쿼리를 컴파일한다 -> 쿼리에 사용한 값을 적절한 데이터 타입으로 바꾼다
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
						// get메서드 사용할때 index로도 사용가능 rs.getString(1);처럼
						count++;
					}
					rs.close();
//					stmt.close();
					pstmt.close();
					connection.close();
					//
					
					System.out.println("DBMS 접속 성공");
				} catch (ClassNotFoundException e) {
				//	e.printStackTrace();
					System.out.println("드라이버 로딩 실패");
				} catch (SQLException e) {
//					e.printStackTrace();
					System.out.println("DBMS 접속 실패");
				}
				}


}
