package controller;

import java.util.Scanner;

import model.CafeVO;

public class CafeController {
	//전체목록 출력
	public void showList() {
		CafeDAO cafeDAO = new CafeDAO();
		cafeDAO.selectAllCafe();
		
	}
	//카페추가
	public void addCafe() {
		Scanner input = new Scanner(System.in);
		
		CafeVO cafe = new CafeVO();
		CafeDAO cafeDAO = new CafeDAO();
		
		System.out.print("카페 이름 입력 : ");
		cafe.setName(input.nextLine());
		System.out.print("카페 주소 입력 : ");
		cafe.setAddress(input.nextLine());
		System.out.print("카페 연락처 입력 : ");
		cafe.setTel(input.nextLine());
		System.out.print("카페 사업자등록증 입력 : ");
		cafe.setLicense(input.nextLine());
		cafe.setState(1);
		
		cafeDAO.insertCafe(cafe);
		

		System.out.println();
		System.out.println("카페 전체 리스트");
		cafeDAO.selectAllCafe();
		System.out.println();
		
	}
	//카페삭제
	public void removeCafe() {
		CafeVO cafe = null;
		CafeDAO cafeDAO = new CafeDAO();
		Scanner input = new Scanner(System.in);
		cafeDAO.selectAllCafe();
		boolean state = true;
		do {
			System.out.print("카페 번호 입력 : ");
			cafe = cafeDAO.selectCafe(input.nextInt());
			
			if(cafe != null) {
				System.out.println(cafe);
				cafeDAO.updateCafe(cafe);
				state = false;
			} else {
				System.out.println("잘못된 카페 번호입니다");
			}
		}while(state);
		
	}

}
