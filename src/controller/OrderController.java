package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.BeanVO;
import model.OrderVO;

public class OrderController {
	static OrderDAO orderDAO = new OrderDAO();

	public void showList() {
		orderDAO.selectAll();

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
			System.out.print("수량 입력(g) : ");
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
