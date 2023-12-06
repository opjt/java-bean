package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CafeVO;

public class CafeDAO {
	public int getTotalConunt() {

		String sql = "select count(*)as count from cafe where state != 0";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result ;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
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

	public void insertCafe(CafeVO cafe) {
		String sql = "insert into cafe (c_no,name,address,tel,license,state) values " + "(cafe_seq.nextval,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cafe.getName());
			pstmt.setString(2, cafe.getAddress());
			pstmt.setString(3, cafe.getTel());
			pstmt.setString(4, cafe.getLicense());
			pstmt.setInt(5, cafe.getState());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(cafe.getName() + "카페 등록 완료.");
			} else {
				System.out.println("카페 등록 실패!!!");
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

	// 카페 삭제
	public void deleteCafe(CafeVO cafe) {
		String sql = "update cafe set state = 0 where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafe.getC_no());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(cafe.getName() + "카페 삭제 완료");

			} else {
				System.out.println("카페 삭제 실패!!!");
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

	// 카페 전체목록출력
	public void selectAllCafe() {

		// 페이지 번호와 페이지당 아이템 수를 기반으로 시작 인덱스 계산

		String sql = "select * from cafe where state != 0";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeVO cafeVO = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("일련번호\t카페이름/카페주소/카페연락처/카페사업자등록증");
			while (rs.next()) {
				cafeVO = new CafeVO();
				cafeVO.setC_no(rs.getInt("c_no"));
				cafeVO.setName(rs.getString("name"));
				cafeVO.setAddress(rs.getString("address"));
				cafeVO.setTel(rs.getString("tel"));
				cafeVO.setLicense(rs.getString("license"));
				System.out.println(cafeVO.getC_no() + "\t" + cafeVO.getName() + "/" + cafeVO.getAddress() + "/"
						+ cafeVO.getTel() + "/" + cafeVO.getLicense());
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

	// 카페아이디 입력받아서 카페정보반환
	public CafeVO selectCafe(int cafeId) {
		String sql = "select * from cafe where c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeVO cafeVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cafeVO = new CafeVO();
				cafeVO.setC_no(rs.getInt("c_no"));
				cafeVO.setName(rs.getString("name"));
				cafeVO.setAddress(rs.getString("address"));
				cafeVO.setTel(rs.getString("tel"));
				cafeVO.setLicense(rs.getString("license"));
				return cafeVO;
			} else {
				System.out.println("찾을 수 없는 카페 아이디입니다");
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
		return cafeVO;

	}

	public void updateCafe(CafeVO cafe) {
		String sql = "UPDATE cafe "
				+ "SET "
				+ "name = ?, "
				+ "address = ?, "
				+ "tel = ?, "
				+ "license = ?, "
				+ "state = ? "
				+ "WHERE "
				+ "c_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cafe.getName());
			pstmt.setString(2, cafe.getAddress());
			pstmt.setString(3, cafe.getTel());
			pstmt.setString(4, cafe.getLicense());
			pstmt.setInt(5, cafe.getState());
			pstmt.setInt(6, cafe.getC_no());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(cafe.getName() + "카페 수정 완료.");
			} else {
				System.out.println("카페 수정 실패!!!");
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

}
