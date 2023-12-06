import java.util.Scanner;

import controller.BeanController;
import controller.CafeController;
import controller.OrderController;
import controller.OrderDAO;
import view.MenuView;

public class Main {

	final static String PW = "1234"; //비밀번호 값
	static boolean loginCookie = false; //false면 로그인요구 true면 로그인됨.

	public static void main(String[] args) {
		mainMenu(); //메인메뉴 호출
	}

	private static void mainMenu() { //메인메뉴
		Scanner input = new Scanner(System.in);
		MenuView menuView = new MenuView(); //메뉴뷰어 생성
		String password; //비밀번호
		try {
			while (true) { //무한반복
				if (!loginCookie) { //로그인 안되어있으면
					System.out.print("관리자 비밀번호를 입력하시오 : ");
					password = input.nextLine();
					
					if (password.equals(PW) == false) {
						System.out.println("잘못된 비밀번호입니다");
					} else { 
						loginCookie = true; 
					}
				}
				if (loginCookie) { //로그인한 상태일 시
					System.out.println("1.원두관리");
					System.out.println("2.카페관리");
					System.out.println("3.발주관리");
					System.out.println("4.수익통계");
					System.out.println("5.프로그램종료");
					int choice = input.nextInt();
					switch (choice) {
					case 1: // 원두관리
						menuView.showBeanMenu(); //원두관리 메뉴로 이동
						break;
					case 2: // 카페관리
						menuView.showCafeMenu(); //카페관리 메뉴로 이동
						break;
					case 3: // 발주관리
						menuView.showOrderMenu(); //발주관리 메뉴로 이동
						break;
					case 4: //수익통계
						OrderDAO orderDAO = new OrderDAO(); //오더DAO 생성
						orderDAO.totalPirce(); //오더DAO로 수익통계 메소드 실행
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
		} catch (Exception e) { //입력예외처리
			System.out.println("\n잘못된 입력입니다 프로그램을 재실행하십시오");
			return;
		}
	}
}
