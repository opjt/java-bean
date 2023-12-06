package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BeanVO;
import model.CafeVO;

public class BeanDAO {
	public void insertBean(BeanVO bean) {
		String sql = "insert into bean (b_no,name,country,area,farm,farmer,altitude,Variety, process,roasting, flavor,volume,makedate,price,cost,state) values "
				+ "(bean_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?, 'YYYY-MM-DD'),?,?,1)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getCountry());
			pstmt.setString(3, bean.getArea());
			pstmt.setString(4, bean.getFarm());
			pstmt.setString(5, bean.getFarmer());
			pstmt.setInt(6, bean.getAltitude());
			pstmt.setString(7, bean.getVariety());
			pstmt.setString(8, bean.getProcess());
			pstmt.setString(9, bean.getRoasting());
			pstmt.setString(10, bean.getFlavor());
			pstmt.setInt(11, bean.getVolume());
			pstmt.setString(12, bean.getMakeDate());
			pstmt.setInt(13, bean.getPrice());
			pstmt.setInt(14, bean.getCost());
			
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(bean.getName() + "원두 등록 완료.");
			} else {
				System.out.println("원두 등록 실패!!!");
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
	public boolean checkSameName(String name) {
		String sql = "select * from bean where state != 0 and name = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				return true;
			
			} else {
				return false;
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
		return false;
		
	}
	
	public void deleteBean(BeanVO beanVO) {
		String sql = "update bean set state = 0 where b_no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, beanVO.getB_no());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(beanVO.getName() + "원두 삭제 완료");
				
			} else {
				System.out.println("원두 삭제 실패!!!");
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
	
	public BeanVO selectBean(int selectId) {
		String sql = "SELECT b_no, name, country, area, farm, farmer, altitude, variety, process, roasting, flavor, volume, TO_CHAR(makedate, 'YYYY-MM-DD') AS makedate, price, cost, state FROM bean"
				+ " where state != 0 and b_no = ? order by b_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BeanVO beanVO = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, selectId);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				beanVO = new BeanVO();
				beanVO.setB_no(rs.getInt("b_no"));
				beanVO.setName(rs.getString("name"));
				beanVO.setCountry(rs.getString("country"));
				beanVO.setArea(rs.getString("area"));
				beanVO.setFarm(rs.getString("Farm"));
				beanVO.setFarmer(rs.getString("Farmer"));
				beanVO.setAltitude(rs.getInt("altitude"));
				beanVO.setVariety(rs.getString("variety"));
				beanVO.setProcess(rs.getString("Process"));
				beanVO.setRoasting(rs.getString("Roasting"));
				beanVO.setFlavor(rs.getString("flavor"));
				beanVO.setVolume(rs.getInt("Volume"));
				beanVO.setMakeDate(rs.getString("makedate"));
				beanVO.setPrice(rs.getInt("price"));
				beanVO.setCost(rs.getInt("cost"));
				return beanVO;
			
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
		return beanVO;
		
	}
	
	public void selectAllBean() {
//		String sql = "select * from bean where state != 0 order by b_no";
		String sql = "SELECT b_no, name, country, area, farm, farmer, altitude, variety, process, roasting, flavor, volume, TO_CHAR(makedate, 'YYYY-MM-DD') AS makedate, price, cost, state FROM bean"
				+ " where state != 0 order by b_no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BeanVO beanVO = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("일련번호\t원두이름-원산지 :: 판매가(원가)-용량");
			System.out.println("────────────────────────────────────────");
			while (rs.next()) {
				beanVO = new BeanVO();
				beanVO.setB_no(rs.getInt("b_no"));
				beanVO.setName(rs.getString("name"));
				beanVO.setCountry(rs.getString("country"));
				beanVO.setVolume(rs.getInt("Volume"));
				beanVO.setPrice(rs.getInt("price"));
				beanVO.setCost(rs.getInt("cost"));
				
				System.out.println(beanVO.getB_no() + "\t" + beanVO.getName() + "-" + beanVO.getCountry() 
				+" :: "  + beanVO.getVolume()  + "g-" + beanVO.getPrice() + "원(" + beanVO.getCost()+ "원)");
			}
			System.out.println("────────────────────────────────────────");
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
	public void updateBean(BeanVO bean) {
			String sql = "UPDATE bean "
					+ "SET "
					+ "name = ?, "
					+ "country =?, "
					+ "area = ?, "
					+ "farm = ?, "
					+ "farmer = ?, "
					+ "altitude = ?, "
					+ "variety = ?, "
					+ "process = ?, "
					+ "roasting = ?, "
					+ "flavor = ?, "
					+ "volume = ?, "
					+ "makedate = TO_DATE(?, 'YYYY-MM-DD'), "
					+ "price = ?, "
					+ "cost = ?, "
					+ "state = ? "
					+ "WHERE "
					+ "b_no = ?";
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getName());
				pstmt.setString(2, bean.getCountry());
				pstmt.setString(3, bean.getArea());
				pstmt.setString(4, bean.getFarm());
				pstmt.setString(5, bean.getFarmer());
				pstmt.setInt(6, bean.getAltitude());
				pstmt.setString(7, bean.getVariety());
				pstmt.setString(8, bean.getProcess());
				pstmt.setString(9, bean.getRoasting());
				pstmt.setString(10, bean.getFlavor());
				pstmt.setInt(11, bean.getVolume());
				pstmt.setString(12, bean.getMakeDate());
				pstmt.setInt(13, bean.getPrice());
				pstmt.setInt(14, bean.getCost());
				pstmt.setInt(15, 1);
				pstmt.setInt(16, bean.getB_no());
				
				int i = pstmt.executeUpdate();
				if (i == 1) {
					System.out.println(bean.getName() + "원두 수정 완료.");
				} else {
					System.out.println("원두 수정 실패!!!");
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
