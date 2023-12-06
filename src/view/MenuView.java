package view;

import java.util.Scanner;

import controller.BeanController;
import controller.CafeController;
import controller.OrderController;

public class MenuView {

	public void showOrderMenu() { // 발주 메뉴
		Scanner input = new Scanner(System.in);
		System.out.println("╔══════════════════════╗");
		System.out.println("║ㅤㅤㅤㅤㅤㅤ발주 관리ㅤㅤㅤㅤㅤ ║");
		System.out.println("╠══════════════════════╣");
		System.out.println("║ 1. 발주조회        ㅤㅤ  ║");
		System.out.println("║ 2. 발주생성 ㅤㅤㅤ  ㅤ   ㅤ║");
		System.out.println("║ 5. 메인메뉴 ㅤㅤ         ║");
		System.out.println("╚══════════════════════╝");
		System.out.print("메뉴를 선택하시오 : ");
		int choice = input.nextInt(); // 메뉴 선택번호
		OrderController orderController = new OrderController(); // 오더 컨트롤러 생성.

		switch (choice) {
		case 1: // 발주 조회
			orderController.showList();
			break;
		case 2: // 발주 생성
			orderController.addOrder();
			break;
		case 3: // 메인메뉴로 이동
			break;
		default: // 예외 값 입력
			System.out.println("잘못된 메뉴 선택입니다");
			break;
		}
	}

	public void showCafeMenu() { // 카페 메뉴
		Scanner input = new Scanner(System.in);

		// ㅤ
		System.out.println("╔══════════════════════╗");
		System.out.println("║ㅤㅤㅤㅤㅤㅤ카페 관리ㅤㅤㅤㅤㅤ ║");
		System.out.println("╠══════════════════════╣");
		System.out.println("║ 1. 카페목록        ㅤㅤ  ║");
		System.out.println("║ 2. 카페추가 ㅤㅤㅤ  ㅤ   ㅤ║");
		System.out.println("║ 3. 카페수정   ㅤㅤ       ║");
		System.out.println("║ 4. 카페삭제 ㅤㅤ         ║");
		System.out.println("║ 5. 메인메뉴 ㅤㅤ         ║");
		System.out.println("╚══════════════════════╝");

		System.err.print("메뉴를 선택하시오 : ");
		int choice = input.nextInt(); // 메뉴 선택번호
		CafeController cafeController = new CafeController(); // 카페 컨트롤러 생성

		switch (choice) {
		case 1: // 카페목록
			cafeController.showList();
			break;
		case 2: // 카페추가
			cafeController.addCafe();
			break;
		case 3: // 카페수정
			cafeController.updateCafe();
			break;
		case 4: // 카페삭제
			cafeController.removeCafe();
			break;
		case 5: // 메인메뉴로 이동
			return;
		default: // 예외 값 입력시
			System.out.println("잘못된 메뉴 선택입니다");
			break;

		}

	}

	public void showBeanMenu() { // 원두 메뉴
		Scanner input = new Scanner(System.in);
//ㅤ
		System.out.println("╔══════════════════════╗");
		System.out.println("║ㅤㅤㅤㅤㅤㅤ원두 관리ㅤㅤㅤㅤㅤ ║");
		System.out.println("╠══════════════════════╣");
		System.out.println("║ 1. 원두목록        ㅤㅤ  ║");
		System.out.println("║ 2. 원두상세정보 ㅤㅤㅤㅤㅤ ㅤ║");
		System.out.println("║ 3. 원두추가   ㅤㅤ       ║");
		System.out.println("║ 4. 원두수정 ㅤㅤ         ║");
		System.out.println("║ 5. 원두삭제 ㅤㅤ         ║");
		System.out.println("║ 6. 메인메뉴 ㅤㅤ         ║");
		System.out.println("╚══════════════════════╝");

		System.out.print("메뉴를 입력하시오 : ");
		int choice = input.nextInt(); // 메뉴 선택번호
		
		BeanController beanController = new BeanController(); // 원두 컨트롤러 생성

		switch (choice) {
		case 1: // 원두목록
			beanController.showList();
			break;
		case 2: // 원두상세정보
			beanController.selectBean();
			break;
		case 3: // 원두추가
			beanController.addBean();
			break;
		case 4: // 원두수정
			beanController.updateBean();
			break;
		case 5: // 원두삭제
			beanController.removeBean();
			break;
		case 6: // 메인메뉴로 이동
			return;
		default: // 예외 값 입력시
			System.out.println("잘못된 메뉴 선택입니다");
			break;
		}

	}
}
