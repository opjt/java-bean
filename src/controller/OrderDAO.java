package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.OrderVO;

public class OrderDAO {

	public void selectAll(String firstDate, String secondDate,int pageNo, int itemsPerPage) {
		// 페이지 번호와 페이지당 아이템 수를 기반으로 시작 인덱스 계산
		int startIndex = ((pageNo - 1) * itemsPerPage) + 1; // (( 페이지번호-1) * 보여줄수) + 1
		int maxCount = 0; //조회방식마다 총페이지 수가 다르니 0으로 사전지정
		String sql = "";
		//0 입력시 전체조회
		if (firstDate.equals("0")) { 
			sql = "SELECT * FROM ("
					+ "    SELECT o.*, c.name AS cafe_name, b.name AS bean_name,to_char(o.o_date, 'YYYY-MM-DD') as odate,"
					+ " ROW_NUMBER() OVER (ORDER BY o_no) AS rnum"
					+ "    FROM orders o INNER JOIN cafe c ON o.c_no = c.c_no"
					+ "    INNER JOIN bean b ON o.b_no = b.b_no"
					+ "    WHERE o.state != 0"
					+ "    ORDER BY o.o_no"
					+ ") "
					+ "WHERE rnum BETWEEN ? AND ?";
			maxCount = (int) 1 + (getTotalConunt("0","0") / itemsPerPage); //최대 페이지수 설정
		} else { //날짜별 조회
			sql = "SELECT * FROM ("
					+ "    SELECT o.*, c.name AS cafe_name, b.name AS bean_name,to_char(o.o_date, 'YYYY-MM-DD') as odate,"
					+ " ROW_NUMBER() OVER (ORDER BY o_no) AS rnum"
					+ "    FROM orders o INNER JOIN cafe c ON o.c_no = c.c_no"
					+ "    INNER JOIN bean b ON o.b_no = b.b_no"
					+ "    WHERE o.state != 0 and o.o_date between '" + firstDate + "' and '" + secondDate +"'"
					+ "    ORDER BY o.o_no"
					+ ") "
					+ "WHERE rnum BETWEEN ? AND ?";
			maxCount = (int) 1 + (getTotalConunt(firstDate,secondDate) / itemsPerPage); //최대 페이지수 설정
		}
		if(pageNo > maxCount) { //페이지번호가 최대 페이주초과일경우
			System.out.println("최대" + maxCount+ "페이지 입니다");
			return;
		} else if(pageNo <= 0){ //페이지 수가 0보다 작을 경우
			System.out.println("잘못된 페이지 접근입니다");
			return;
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO orderVO = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startIndex); //시작할 index
			pstmt.setInt(2, startIndex + itemsPerPage -1); //까지 보여줄 index
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setO_no(rs.getInt("o_no")); // 발주번호
				orderVO.setC_no(rs.getInt("c_no")); // 카페번호
				orderVO.setB_no(rs.getInt("b_no")); // 원두번호
				orderVO.setVolume(rs.getInt("volume")); // 수량
				orderVO.setO_date(rs.getString("odate")); // 발주날짜
				orderVO.setPrice(rs.getInt("price")); // 가격
				orderVO.setAddress(rs.getString("address")); // 주소
	
				System.out.printf("발주번호: %-4d  카페이름: %-17s  원두이름: %-18s  수량: %-3d  발주날짜: %-12s  가격: %-6d  배송지: %-30s\n",
				            orderVO.getO_no(),rs.getString("cafe_name"), rs.getString("bean_name"),
				            orderVO.getVolume(), orderVO.getO_date(), orderVO.getPrice(), orderVO.getAddress());
			}
			System.out.println(pageNo + " / " + maxCount );
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
	//데이터 총개수 리턴
	private int getTotalConunt(String firstDate, String secondDate) {
		String sql = "";
		
		if(firstDate.equals("0")) {//0입력시 전체조
			sql = "select count(*)as count from orders where state != 0";	
		} else { //첫번째 매개변수 0입력 아닐시 날짜별 조회
			sql = "select count(*)as count from orders o where state != 0 and o.o_date between '" + firstDate
					+ "' and '" + secondDate + "' ORDER BY o.o_no";
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result ; //리턴값
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count"); //count 리턴
				return result;
			} else {
				return 0;
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
		return -1;
	}
	//발주 추가(ADD)
	public void insertOrder(OrderVO orderVO) {
		String sql = "insert into orders (o_no,c_no,b_no,volume,o_date,price,address,state) values "
				+ "(orders_seq.nextval,?,?,?,sysdate,?,?,1)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderVO.getC_no()); //카페번호
			pstmt.setInt(2, orderVO.getB_no()); //원두번호
			pstmt.setInt(3, orderVO.getVolume()); //수량
			pstmt.setInt(4, orderVO.getPrice());  //가격
			pstmt.setString(5, orderVO.getAddress()); //배달지
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
