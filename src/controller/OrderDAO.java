package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.OrderVO;

public class OrderDAO {

	public void selectAll(String firstDate, String secondDate) {

//		String sql = "SELECT * from orders"
//				+ " where state != 0 order by o_no";
		String sql = "";
		if (firstDate.equals("0")) {
			sql = "SELECT o.*, c.name AS cafe_name, b.name AS bean_name,to_char(o.o_date, 'YYYY-MM-DD') as odate "
					+ "FROM orders o" + " INNER JOIN cafe c ON o.c_no = c.c_no"
					+ " INNER JOIN bean b ON o.b_no = b.b_no" + " WHERE o.state != 0" + " ORDER BY o.o_no";
		} else {
			sql = "SELECT o.*, c.name AS cafe_name, b.name AS bean_name,to_char(o.o_date, 'YYYY-MM-DD') as odate "
					+ "FROM orders o" + " INNER JOIN cafe c ON o.c_no = c.c_no"
					+ " INNER JOIN bean b ON o.b_no = b.b_no" + " WHERE o.state != 0 and o.o_date between '" + firstDate
					+ "' and '" + secondDate + "'" + " ORDER BY o.o_no";
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("발주번호\t\t카페이름\t\t원두이름\t\t수량\t\t발주날짜\t\t가격\t\t배송지");
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setO_no(rs.getInt("o_no")); // 발주번호
				orderVO.setC_no(rs.getInt("c_no")); // 카페번호
				orderVO.setB_no(rs.getInt("b_no")); // 원두번호
				orderVO.setVolume(rs.getInt("volume")); // 수량
				orderVO.setO_date(rs.getString("odate")); // 발주날짜
				orderVO.setPrice(rs.getInt("price")); // 가격
				orderVO.setAddress(rs.getString("address")); // 주소

				System.out.println(orderVO.getO_no() + "\t\t" + rs.getString("cafe_name") + "\t\t"
						+ rs.getString("bean_name") + "\t\t" + orderVO.getVolume() + "\t\t" + orderVO.getO_date()
						+ "\t\t" + orderVO.getPrice() + "\t\t" + orderVO.getAddress());

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}

	}

	public void insertOrder(OrderVO orderVO) {
		String sql = "insert into orders (o_no,c_no,b_no,volume,o_date,price,address,state) values "
				+ "(orders_seq.nextval,?,?,?,sysdate,?,?,1)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderVO.getC_no());
			pstmt.setInt(2, orderVO.getB_no());
			pstmt.setInt(3, orderVO.getVolume());
			pstmt.setInt(4, orderVO.getPrice());
			pstmt.setString(5, orderVO.getAddress());
			int i = pstmt.executeUpdate();

			if (i == 1) {
				System.out.println("발주 등록 완료.");
			} else {
				System.out.println("발주 등록 실패!!!");
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	public void totalPirce() {
		String sql = "SELECT AVG(o.b_no) AS b_no, " + "b.name, "
				+ "TO_CHAR(SUM(o.volume), '999,999,999') AS total_volume, "
				+ "TO_CHAR(SUM(o.price * o.volume), '999,999,999') AS total_price, "
				+ "TO_CHAR(SUM((o.price * o.volume) - (b.cost * o.volume)), '999,999,999') AS total_get_money, "
				+ "TO_CHAR(AVG(b.cost), '999,999,999') AS avg_cost " + "FROM orders o " + "INNER JOIN "
				+ "bean b ON b.b_no = o.b_no " + "GROUP BY b.name " + "ORDER BY " + "total_price DESC";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
//			System.out.println("원두이름(no) : 총판매개수-누적판매금액[순이익]");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "(n." + rs.getInt("b_no") + ") : 총"
						+ rs.getString("total_volume").trim() + "개 누적판매:" + rs.getString("total_price").trim() + "원[순이익:"
						+ rs.getString("total_get_money").trim() + "원]");

			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}

	}

}
