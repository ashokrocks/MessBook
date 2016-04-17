package org.eclipse.wb.swt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConn {
	Statement RetStat() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb", "root", "raceison10");
			Statement stm = conn.createStatement();
			return stm;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
		

	}
}
