package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBMS {
	public static Connection getConnection() {
		try {
			// DB에 접속
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/maria/dbms");
			
			Connection conn = ds.getConnection();
			
			return conn;
		}catch(NamingException e) {
			System.out.println("Connection Pool과 관련된 예외가 발생");
		} catch (SQLException e) {
			System.out.println("Connection 관련된 예외가 발생");
		}
		
		return null;
	
	}
	
}
