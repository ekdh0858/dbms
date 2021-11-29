package chapter1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMSTest {

	public static void main(String[] args) {
		
		// ����̹��� �ε��Ϸ��� Class.forName �޼��带 ����ؾ���
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			
			System.out.println("DBMS ���� ��");
		// JDBC Driver �� ����ؼ� DBMS�� ����
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=0000");
			
			Statement stmt = connection.createStatement();
			
			String sql = "SELECT * FROM employees LIMIT 5";
			
			ResultSet rs =stmt.executeQuery(sql);
			// executeUpdate �� int���·� ���� ��ȯ
			
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
				System.out.println("gender="+gender);
				System.out.println("hire_date="+hire_date);
				// get�޼��� ����Ҷ� index�ε� ��밡�� rs.getString(1);ó��
				count++;
			}
			rs.close();
			stmt.close();
			connection.close();
			//
			
			System.out.println("DBMS ���� ����");
		} catch (ClassNotFoundException e) {
		//	e.printStackTrace();
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("DBMS ���� ����");
		}
		
		
	}

}
