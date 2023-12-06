package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CafeVO;

public class CafeDAO {
	//카페 전체수 int 리턴
	public int getTotalConunt() {
		String sql = "select count(*) as count from cafe where state != 0";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result ; //결과값 
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count"); //카페 전체수 값설정
				return result; //결과값 리턴
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
	//카페 정보 추가(ADD)
	public void insertCafe(CafeVO cafe) {
		String sql = "insert into cafe (c_no,name,address,tel,license,state) values " + "(cafe_seq.nextval,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cafe.getName()); //카페이름
			pstmt.setString(2, cafe.getAddress()); //카페주소
			pstmt.setString(3, cafe.getTel()); //카페 연락처
			pstmt.setString(4, cafe.getLicense()); //카페 사업자등록증 
			pstmt.setInt(5, cafe.getState()); //카페 상태 1= 표시, 0=표시안함
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

	// 카페 삭제(DELETE)
	public void deleteCafe(CafeVO cafe) {
		String sql = "update cafe set state = 0 where c_no = ?"; //카페 상태를 0으로 변환하여 기능적 삭제 구현
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafe.getC_no()); //카페 번호
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

	// 카페 전체목록출력(READ)
	public void selectAllCafe() {
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
				cafeVO.setC_no(rs.getInt("c_no")); //카페번호
				cafeVO.setName(rs.getString("name")); //카페이름
				cafeVO.setAddress(rs.getString("address")); //카페주소
				cafeVO.setTel(rs.getString("tel")); //카페번호 
				cafeVO.setLicense(rs.getString("license")); //카페 사업자등록증
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

	// 카페아이디 입력받아서 카페정보 리턴
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
				cafeVO.setC_no(rs.getInt("c_no")); //카페 번호
				cafeVO.setName(rs.getString("name")); //카페 이름
				cafeVO.setAddress(rs.getString("address")); //카페 주소  
				cafeVO.setTel(rs.getString("tel")); //카페번호
				cafeVO.setLicense(rs.getString("license")); //카페 사업자등록증
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
	//카페 정보수정(UPDATE)
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
			pstmt.setString(1, cafe.getName()); //카페이름
			pstmt.setString(2, cafe.getAddress()); //카페주소
			pstmt.setString(3, cafe.getTel()); //카페 전화번호
			pstmt.setString(4, cafe.getLicense()); //카페사업자등록증
			pstmt.setInt(5, cafe.getState()); //카페상태 1= 표시, 0=표시안함
			pstmt.setInt(6, cafe.getC_no()); //카페번호
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
