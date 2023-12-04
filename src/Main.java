import java.util.Scanner;

import controller.BeanController;
import controller.CafeController;

public class Main {
	
	final static String PW = "1234";
	static boolean loginCookie = false;
	
	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		Scanner input = new Scanner(System.in);
		while(true) {
			
			String password;
			if(loginCookie == false) {
				System.out.print("관리자 비밀번호를 입력하시오 : ");
				
				password = input.nextLine();
				
				if(password.equals(PW) == false) {
					System.out.println("잘못된 비밀번호입니다");
					
				} else {
					loginCookie = true;
				}
			}
			
			System.out.println("1.원두관리");
			System.out.println("2.카페관리");
			System.out.println("3.발주관리");
			System.out.println("4.수익통계");
			System.out.println("5.프로그램종료");
			int choice = input.nextInt();
			switch(choice) {
			case 1: //원두
				showBeanMenu();
				break;
			case 2: //카페
				showCafeMenu();
				break;
			case 3: //발주
				showOrderMenu();
				break;
			case 5:
				System.out.println("프로그램 종료");
				return;
				
			default:
				System.out.println("잘못된 선택입니다");
				break;
					
			}
			
			
		}
	}

	private static void showOrderMenu() {
		System.out.println("1.발주조회");
		System.out.println("2.발주취소");
		System.out.println("3.메인메뉴");
	
		
	}

	private static void showCafeMenu() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("1.카페목록");
		System.out.println("2.카페추가");
		System.out.println("3.카페삭제");
		System.out.println("4.메인메뉴");
		
		int choice = input.nextInt();
		CafeController cafeController = new CafeController();
		
		switch(choice) {
		case 1:
			cafeController.showList();
			break;
		case 2:
			cafeController.addCafe();
			break;
		case 3:
			cafeController.removeCafe();
			break;
		case 4:
			return;
		default:
			System.out.println("잘못된 메뉴 선택입니다");
			break;
			
		}
		
		
	}

	private static void showBeanMenu() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("1.원두목록");
		System.out.println("2.원두상세정보");
		System.out.println("3.원두추가");
		System.out.println("4.원두삭제");
		System.out.println("5.메인메뉴");
		
		int choice = input.nextInt();
		BeanController beanController = new BeanController();
		
		switch(choice) {
		case 1:
			beanController.showList();
			break;
		case 2:
			beanController.selectBean();
			break;
		case 3:
			beanController.addBean();
			break;
		case 4:
			beanController.removeBean();
			break;
		case 5:
			return;
		default:
			System.out.println("잘못된 메뉴 선택입니다");
			break;
		}
		
	}
}
