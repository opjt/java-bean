package view;

import java.util.Scanner;

import controller.BeanController;
import controller.CafeController;
import controller.OrderController;

public class MenuView {
	public static Scanner choice = new Scanner(System.in); // 메인 메뉴
	
	public void showOrderMenu() {
		Scanner input = new Scanner(System.in);

		System.out.println("1.발주조회");
		System.out.println("2.발주생성");
		System.out.println("3.메인메뉴");

		int choice = input.nextInt();
		OrderController orderController = new OrderController();

		switch (choice) {
		case 1: //발주 조회
			orderController.showList();
			break;
		case 2: //발주 생성
			orderController.addOrder();
			break;
		case 3: //메인메뉴
			break;
		case 4:
			return;
		default:
			System.out.println("잘못된 메뉴 선택입니다");
			break;

		}

	}

	public void showCafeMenu() {
		Scanner input = new Scanner(System.in);

		System.out.println("1.카페목록");
		System.out.println("2.카페추가");
		System.out.println("3.카페수정");
		System.out.println("4.카페삭제");
		System.out.println("5.메인메뉴");

		int choice = input.nextInt();
		CafeController cafeController = new CafeController();

		switch (choice) {
		case 1: //카페목록
			cafeController.showList();
			break;
		case 2: //카페추가
			cafeController.addCafe();
			break;
		case 3: //카페수정
			cafeController.updateCafe();
			break;
		case 4: //카페삭제
			cafeController.removeCafe();
			break;
		case 5:
			return;
		default:
			System.out.println("잘못된 메뉴 선택입니다");
			break;

		}

	}

	public void showBeanMenu() {
		Scanner input = new Scanner(System.in);

		System.out.println("1.원두목록");
		System.out.println("2.원두상세정보");
		System.out.println("3.원두추가");
		System.out.println("4.원두수정");
		System.out.println("5.원두삭제");
		System.out.println("6.메인메뉴");

		int choice = input.nextInt();
		BeanController beanController = new BeanController();

		switch (choice) {
		case 1: //원두목록
			beanController.showList();
			break;
		case 2: //원두상세정보
			beanController.selectBean();
			break;
		case 3: //원두추가
			beanController.addBean();
			break;
		case 4: //원두수정
			beanController.updateBean();
			break;
		case 5: //원두삭제
			beanController.removeBean();
			break;
		case 6:
			return;
		default:
			System.out.println("잘못된 메뉴 선택입니다");
			break;
		}

	}
}
