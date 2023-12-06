package controller;

import java.util.Scanner;

import model.BeanVO;
import model.OrderVO;

public class OrderController {
	static OrderDAO orderDAO = new OrderDAO();
	
	//발주 조회
	public void showList() {
		Scanner input = new Scanner(System.in);
		int choice;
		
		System.out.println("1. 전체조회");
		System.out.println("2. 날짜조회");
		System.out.print("메뉴를 선택하시오 : ");
		int pageCount = 1; //처음 페이지번호
		int showCount = 10; //한번에 보여줄양
		
		choice = input.nextInt(); //선택한 메뉴
		
		if(choice == 1) { //전체조회
			
			Scanner input2 = new Scanner(System.in);
			while(true) {
				orderDAO.selectAll("0","0",pageCount,showCount); //0,0 입력시 전체조회 	
				System.out.println("다음/이전/나가기");
				String select = input2.nextLine();
				
				if(select.equals("다음")) { //다음 입력시
					pageCount++; //다음 입력시 페이지 수 +1
				} else if(select.equals("이전")) { //이전 입력시 
					pageCount--;//이전 입력시 페이지 수 -1
				} else { //아니면 탈출
					break;
				}
			}
		} else if(choice == 2) { //날짜조회시
			boolean checkDate = true; //true면 반복 false 반복탈출
			String firstDate = null;
			String secondDate = null;
			Scanner input2 = new Scanner(System.in);
			
			while(true) {
				if(checkDate) { //날짜 입력 안했을 경우
					System.out.print("조회할 날짜를 입력하시오(ex:'2023-12-01 2023-12-22'띄어쓰기로 구분) : ");

					firstDate = input.next();
					secondDate = input.next();
				}
				if(DBUtil.isDate(firstDate) && DBUtil.isDate(secondDate)) {//입력한 날짜값 날짜형식인지 검사
					checkDate = false; //날짜입력 반복 탈출
					orderDAO.selectAll(firstDate, secondDate,pageCount,showCount);	
					System.out.println("다음/이전/나가기");
					String select = input2.nextLine();
					if(select.equals("다음")) {
						pageCount++; //다음 입력시 페이지카운트 +1
					} else if(select.equals("이전")) {
						pageCount--;//이전 입력시 페이지카운트 -1
					} else { // 아니면 탈출
						break;
					}
				} else {
					System.out.println("잘못된 날짜 형식입니다");
				}
			}
		}

	}
	//발주 추가
	public void addOrder() {
		Scanner input = new Scanner(System.in);
		BeanDAO beanDAO = new BeanDAO();
		CafeDAO cafeDAO = new CafeDAO();

		OrderVO orderVO = new OrderVO();
		BeanVO beanVO = null;
		
		beanDAO.selectAllBean(); //원두전체목록
		
		while (true) {
			System.out.print("원두 번호를 입력하시오 : ");
			int no = input.nextInt();

			beanVO = beanDAO.selectBean(no); //입력받은 번호로 원두 정보 리턴

			if (beanVO != null) {
				orderVO.setB_no(no); //발주정보에 원두번호 입력
				break; //반복 탈출
			}
		}
		cafeDAO.selectAllCafe(); //카페전체목록
		while (true) {
			System.out.print("카페 번호를 입력하시오 : ");
			int no = input.nextInt(); 

			if (cafeDAO.selectCafe(no) != null) {//입력받은 번호로 카페 정보 리턴 null이 아닐시
				orderVO.setC_no(no); //발주정보에 카페번호 입력
				break; //반복 탈출
			}
		}

		input = new Scanner(System.in); //엔터 상쇄

		//수량 숫자 입력 반복
		while (true) {
			System.out.print("수량 입력(개수) : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) { //숫자형태인지 확인
				orderVO.setVolume(Integer.parseInt(value)); //int형으로 변환후 값 설정
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//가격 숫자 입력 반복
		while (true) {
			System.out.print("가격 입력 (판매가:" +  beanVO.getPrice()+") : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) { //숫자형태인지 확인
				orderVO.setPrice(Integer.parseInt(value)); //int형으로 변환후 값 설정
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//배송지 입력
		System.out.print("배송지를 입력하시오 : ");
		orderVO.setAddress(input.nextLine());
		//값 추가
		orderDAO.insertOrder(orderVO);
		

	}

}
