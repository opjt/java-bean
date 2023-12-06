package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BeanVO;
import model.CafeVO;

public class BeanDAO {
	
	//원두 데이터 추가(Add)
	public void insertBean(BeanVO bean) {
		String sql = "insert into bean (b_no,name,country,area,farm,farmer,altitude,Variety, process,roasting, flavor,volume,makedate,price,cost,state) values "
				+ "(bean_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?, 'YYYY-MM-DD'),?,?,1)";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getName()); //원두이름
			pstmt.setString(2, bean.getCountry()); //원두출산지
			pstmt.setString(3, bean.getArea()); //원두지배지
			pstmt.setString(4, bean.getFarm()); //원두농장
			pstmt.setString(5, bean.getFarmer()); //원두농부
			pstmt.setInt(6, bean.getAltitude()); //원두 해발고도
			pstmt.setString(7, bean.getVariety()); //원두 품종
			pstmt.setString(8, bean.getProcess()); //원두 가공방식
			pstmt.setString(9, bean.getRoasting());//원두 볶음도
			pstmt.setString(10, bean.getFlavor());//원두향미
			pstmt.setInt(11, bean.getVolume());//원두 용량
			pstmt.setString(12, bean.getMakeDate()); //원두 제조일
			pstmt.setInt(13, bean.getPrice()); //원두 가격
			pstmt.setInt(14, bean.getCost()); //원두 원가
			
			int i = pstmt.executeUpdate();
			if (i == 1) { //정상적으로 insert되었으면
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
	//원두 이름 중복 검사
	public boolean checkSameName(String name) {
		String sql = "select * from bean where state != 0 and name = ?"; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); //매개변수로 받은 원두 이름 값 설정
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
	//원두 삭제
	public void deleteBean(BeanVO beanVO) {
		String sql = "update bean set state = 0 where b_no = ?"; //delete문으로 삭제하는 것이 아닌 state를 0으로 기능적 삭제 구현
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, beanVO.getB_no()); //원두 번호 
			int i = pstmt.executeUpdate();
			if (i == 1) { //쿼리문 정상적으로 실행시 
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
	
	//원두 아이디로 원두 정보 리턴
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
			pstmt.setInt(1, selectId); //매개변수로 받은 원두아이디
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				beanVO = new BeanVO();
				beanVO.setB_no(rs.getInt("b_no")); //원두번호
				beanVO.setName(rs.getString("name")); //원두이름
				beanVO.setCountry(rs.getString("country")); //원두출산지
				beanVO.setArea(rs.getString("area")); //원두재배지
				beanVO.setFarm(rs.getString("Farm")); //원두농장
				beanVO.setFarmer(rs.getString("Farmer"));//원두농부
				beanVO.setAltitude(rs.getInt("altitude")); //원두해발고도
				beanVO.setVariety(rs.getString("variety")); //원두품종
				beanVO.setProcess(rs.getString("Process")); //원두가공방식
				beanVO.setRoasting(rs.getString("Roasting")); //원두볶음도
				beanVO.setFlavor(rs.getString("flavor")); //원두 향미
				beanVO.setVolume(rs.getInt("Volume")); //원두용량
				beanVO.setMakeDate(rs.getString("makedate")); //원두 제조일
				beanVO.setPrice(rs.getInt("price")); //원두 가격
				beanVO.setCost(rs.getInt("cost")); //원두 원가
				return beanVO; //원두정보 리턴
			
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
	
	//원두 전체목록 출력
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
				beanVO.setB_no(rs.getInt("b_no")); //원두번호
				beanVO.setName(rs.getString("name")); //원두 이름
				beanVO.setCountry(rs.getString("country")); //원두 원산지
				beanVO.setVolume(rs.getInt("Volume")); //원두 용량
				beanVO.setPrice(rs.getInt("price")); //원두 가격
				beanVO.setCost(rs.getInt("cost")); //원두 원가
				
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
	
	//원두 정보수정 쿼리
	public void updateBean(BeanVO bean) {
			String sql = "UPDATE bean "
					+ "SET "
					+ "name = ?, " //이름
					+ "country =?, " //원산지
					+ "area = ?, " //지배지
					+ "farm = ?, " //농장
					+ "farmer = ?, " //농부
					+ "altitude = ?, " //해발고도
					+ "variety = ?, " //품종
					+ "process = ?, " //가공방식
					+ "roasting = ?, " //볶음도
					+ "flavor = ?, " //향미
					+ "volume = ?, " //용량
					+ "makedate = TO_DATE(?, 'YYYY-MM-DD'), " //제조일
					+ "price = ?, "//가격
					+ "cost = ?, "// 원가
					+ "state = ? " //상태
					+ "WHERE "
					+ "b_no = ?"; //원두번호
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getName()); //원두이름
				pstmt.setString(2, bean.getCountry());//원두원산지
				pstmt.setString(3, bean.getArea());//지배지
				pstmt.setString(4, bean.getFarm());//농장
				pstmt.setString(5, bean.getFarmer());//농부
				pstmt.setInt(6, bean.getAltitude());//해발고도
				pstmt.setString(7, bean.getVariety()); //품종
				pstmt.setString(8, bean.getProcess()); // 가공방식
				pstmt.setString(9, bean.getRoasting()); //볶음도
				pstmt.setString(10, bean.getFlavor()); //향미
				pstmt.setInt(11, bean.getVolume()); //용량
				pstmt.setString(12, bean.getMakeDate()); //제조일
				pstmt.setInt(13, bean.getPrice()); //가격 
				pstmt.setInt(14, bean.getCost()); //원가
				pstmt.setInt(15, 1); //state  1= 표시 
				pstmt.setInt(16, bean.getB_no()); //원두 번호
				
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
