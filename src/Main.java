import java.util.Scanner;

import controller.BeanController;
import controller.CafeController;
import controller.OrderController;
import controller.OrderDAO;
import view.MenuView;

public class Main {

	final static String PW = "1234";
	static boolean loginCookie = true;

	public static void main(String[] args) {
		mainMenu();
	}

	private static void mainMenu() {
		Scanner input = new Scanner(System.in);
		MenuView menuView = new MenuView();
		String password;
		try {
			while (true) {

				if (loginCookie) {
					System.out.print("관리자 비밀번호를 입력하시오 : ");
					password = input.nextLine();
					if (password.equals(PW) == false) {
						System.out.println("잘못된 비밀번호입니다");
					} else {
						loginCookie = false;
					}
				}
				if (!loginCookie) {
					System.out.println("1.원두관리");
					System.out.println("2.카페관리");
					System.out.println("3.발주관리");
					System.out.println("4.수익통계");
					System.out.println("5.프로그램종료");
					int choice = input.nextInt();
					switch (choice) {
					case 1: // 원두
						menuView.showBeanMenu();
						break;
					case 2: // 카페
						menuView.showCafeMenu();
						break;
					case 3: // 발주
						menuView.showOrderMenu();
						break;
					case 4:
						OrderDAO orderDAO = new OrderDAO();
						orderDAO.totalPirce();
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
		} catch (Exception e) {
			System.out.println("\n잘못된 입력입니다 프로그램을 재실행하십시오");
			return;
		}
	}
}
