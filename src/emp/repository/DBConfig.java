package emp.repository;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.*;

public class DBConfig {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	public DBConfig() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeproject","root","Ajay@1234");
			}
		catch(Exception ex) {
			System.out.println("Error is ajay"+ex);
		}
	}
}
