package chapter1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMSTest {

	public static void main(String[] args) {
		
		// 드라이버를 로딩하려면 Class.forName 메서드를 사용해야함
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
			System.out.println("DBMS 접속 전");
		// JDBC Driver 를 사용해서 DBMS에 접속
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=0000");
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM employees LIMIT 5";
			
			ResultSet rs =stmt.executeQuery(sql);
			// executeUpdate 는 int형태로 값을 반환
			
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
				System.out.println("gender="+gender);
				System.out.println("hire_date="+hire_date);
				// get메서드 사용할때 index로도 사용가능 rs.getString(1);처럼
				count++;
			}
			rs.close();
			stmt.close();
			connection.close();
			//
			
			System.out.println("DBMS 접속 성공");
		} catch (ClassNotFoundException e) {
		//	e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("DBMS 접속 실패");
		}
		
		
	}

}
