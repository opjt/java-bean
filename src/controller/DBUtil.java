package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "hr", "1234");
//		System.out.println("DB 연결 성공");
		return con;
	}

	public static boolean isInteger(String strValue) {
		try {
			Integer.parseInt(strValue);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isDate(String checkDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			dateFormat.setLenient(false);
			dateFormat.parse(checkDate);
			return true;

		} catch (ParseException e) {
			return false;
		}

	}

}