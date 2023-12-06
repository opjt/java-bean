package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; //db주소

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "hr", "1234"); //주소,db,pw
//		System.out.println("DB 연결 성공");
		return con;
	}

	public static boolean isInteger(String strValue) { //문자열이 숫자인지 확인 
		try {
			Integer.parseInt(strValue); 
			return true;
		} catch (NumberFormatException ex) { 
			return false;
		}
	}

	public static boolean isDate(String checkDate) { //문자열이 날짜 형식인지 확인
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