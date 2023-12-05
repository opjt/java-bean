package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.BeanVO;
import model.OrderVO;

public class OrderController {
	static OrderDAO orderDAO = new OrderDAO();

	public void showList() {
		Scanner input = new Scanner(System.in);
		int choice;
		
		System.out.println("1. 전체조회");
		System.out.println("2. 날짜조회");
		System.out.print("메뉴를 선택하시오 : ");
		int pageCount = 1; //페이지번호
		int showCount = 10; //한번에 보여줄양
		choice = input.nextInt();
		if(choice == 1) {
			
			Scanner input2 = new Scanner(System.in);
			while(true) {
				orderDAO.selectAll("0","0",pageCount,showCount); 	
				System.out.println("다음/이전/나가기");
				String select = input2.nextLine();
				if(select.equals("다음")) {
					pageCount++;
				} else if(select.equals("이전")) {
					pageCount--;
				} else {
					break;
				}
			}
		} else if(choice == 2) {
			boolean checkDate = true;
			String firstDate = null;
			String secondDate = null;
			Scanner input2 = new Scanner(System.in);
			while(true) {
				if(checkDate) {
					System.out.print("조회할 날짜를 입력하시오(ex:'2023-12-01 2023-12-22'띄어쓰기로 구분) : ");

					firstDate = input.next();
					secondDate = input.next();
				}
				if(DBUtil.isDate(firstDate) && DBUtil.isDate(secondDate)) {
					checkDate = false;
					orderDAO.selectAll(firstDate, secondDate,pageCount,showCount);	
					System.out.println("다음/이전/나가기");
					String select = input2.nextLine();
					if(select.equals("다음")) {
						pageCount++;
					} else if(select.equals("이전")) {
						pageCount--;
					} else {
						break;
					}
				} else {
					System.out.println("잘못된 날짜 형식입니다");
				}
			}
		}

	}

	public void addOrder() {
		Scanner input = new Scanner(System.in);
		BeanDAO beanDAO = new BeanDAO();
		CafeDAO cafeDAO = new CafeDAO();

		OrderVO orderVO = new OrderVO();
		BeanVO beanVO = null;
		
		beanDAO.selectAllBean();
		while (true) {
			System.out.print("원두 번호를 입력하시오 : ");
			int no = input.nextInt();

			beanVO = beanDAO.selectBean(no);

			if (beanVO != null) {
				orderVO.setB_no(no);
				break;
			}
		}
		cafeDAO.selectAllCafe();
		while (true) {
			System.out.print("카페 번호를 입력하시오 : ");
			int no = input.nextInt();

			if (cafeDAO.selectCafe(no) != null) {
				orderVO.setC_no(no);
				break;
			}
		}

		input = new Scanner(System.in);

		while (true) {
			System.out.print("수량 입력(개수) : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				orderVO.setVolume(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}

		while (true) {
			System.out.print("가격 입력 (판매가:" +  beanVO.getPrice()+") : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				orderVO.setPrice(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		System.out.print("배송지를 입력하시오 : ");
		orderVO.setAddress(input.nextLine());
		orderDAO.insertOrder(orderVO);
		

	}

}
