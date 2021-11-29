package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBMS {
	public static Connection getConnection() {
		try {
			// DB�� ����
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/maria/dbms");
			
			Connection conn = ds.getConnection();
			
			return conn;
		}catch(NamingException e) {
			System.out.println("Connection Pool�� ���õ� ���ܰ� �߻�");
		} catch (SQLException e) {
			System.out.println("Connection ���õ� ���ܰ� �߻�");
		}
		
		return null;
	
	}
	
}
