package controller;

import java.util.Scanner;

import model.CafeVO;

public class CafeController {
	
	
	//카페 목록
	public void showList() {
		CafeDAO cafeDAO = new CafeDAO(); //카페DAO 생성
		cafeDAO.selectAllCafe(); //카페전체목록 출력	
	}
	//카페추가
	public void addCafe() {
		Scanner input = new Scanner(System.in);
		CafeDAO cafeDAO = new CafeDAO(); //카페DAO 생성
		
		CafeVO cafe = new CafeVO();
		//카페 이름입력
		System.out.print("카페 이름 입력 : ");
		cafe.setName(input.nextLine());
		//카페 주소 입력
		System.out.print("카페 주소 입력 : ");
		cafe.setAddress(input.nextLine());
		//카페 연락처 입력
		System.out.print("카페 연락처 입력 : ");
		cafe.setTel(input.nextLine());
		//카페 사업자등록증 입력
		System.out.print("카페 사업자등록증 입력 : ");
		cafe.setLicense(input.nextLine());
		//카페 상태 설정 1= 표시 , 0=표시안함
		cafe.setState(1);
		
		cafeDAO.insertCafe(cafe); //카페 insert

		System.out.println();
		System.out.println("카페 전체 리스트");
		cafeDAO.selectAllCafe(); //카페 전체목록 출력
		System.out.println();
		
	}
	//카페 정보 수정
	public void updateCafe() {
		Scanner input = new Scanner(System.in);
		CafeDAO cafeDAO = new CafeDAO(); //카페DAO 생성
		
		CafeVO cafe = new CafeVO();
		boolean state = true; //반복상태 true=반복
		
		do {
			cafeDAO.selectAllCafe(); //카페전체목록 출력
			System.out.print("카페 번호 입력 : "); 
			int cafeId = input.nextInt(); //카페 번호 값 저장
			cafe = cafeDAO.selectCafe(cafeId); //카페번호로 카페정보 불러오기
			
			if(cafe != null) { //불러온 카페정보가 null이 아니라면
				cafe.setC_no(cafeId); //카페 번호 설정 
				state = false; //무한반복 탈출
			} else {
				System.out.println("잘못된 카페 번호입니다");
			}
		}while(state);
		
		input.nextLine(); //nextint 개행값 상쇄
		
		//카페 이름 수정
		System.out.print("카페 이름 입력 [" + cafe.getName() + "]  : ");
		cafe.setName(input.nextLine());
		//카페 주소 수정
		System.out.print("카페 주소 입력 [" + cafe.getAddress() + "]  : ");
		cafe.setAddress(input.nextLine());
		//카페 안락처 수정
		System.out.print("카페 연락처 입력 [" + cafe.getTel() + "]  : ");
		cafe.setTel(input.nextLine());
		//카페 사업자등록증 수정
		System.out.print("카페 사업자등록증 입력 [" + cafe.getLicense() + "]  : ");
		cafe.setLicense(input.nextLine());
		//카페 상태 설정 1 = 표시 , 0 = 표시 안함
		cafe.setState(1);
		
		cafeDAO.updateCafe(cafe);//카페 정보 수정
		System.out.println();
		
	}
	//카페삭제
	public void removeCafe() {
		CafeVO cafe = null;
		Scanner input = new Scanner(System.in);
		CafeDAO cafeDAO = new CafeDAO(); //카페DAO 생성
		
		boolean state = true; //반복상태 true면 반복
		do {
			cafeDAO.selectAllCafe(); //카페전체목록 출력
			System.out.print("카페 번호 입력 : ");
			cafe = cafeDAO.selectCafe(input.nextInt()); //입력받은 카페번호로 카페 객체 불러옴
			
			if(cafe != null) { //불러온 카페 정보가 null이 아닐 경우
				
				cafeDAO.deleteCafe(cafe); //카페 삭제 
				state = false; //무한 반복 탈출
			} else {
				System.out.println("잘못된 카페 번호입니다");
			}
		}while(state);
		
	}

}
