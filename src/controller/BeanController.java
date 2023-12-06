package controller;

import java.util.Scanner;

import model.BeanVO;
import model.CafeVO;

public class BeanController {
	static BeanDAO beanDAO = new BeanDAO(); //원두DAO 생성

	//원두 전체목록
	public void showList() { 
		beanDAO.selectAllBean(); //원두 전체목록
	}
	
	//원두 수정
	public void updateBean() {
		Scanner input = new Scanner(System.in);
		BeanVO bean = new BeanVO(); //원두VO 
		
		boolean state = true; //do-while 상태 true=면 무한반복
		
		do {
			beanDAO.selectAllBean(); //원두 전체목록 불러오기
			
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt(); //입력받은 원두아이디
			
			bean = beanDAO.selectBean(beanId); //입력받은 아이디로 원두VO 불러오기
			
			if (bean != null) { //불러온 원두VO 가 null이 아닐 경우
				bean.setB_no(beanId); //원두아이디 설정
				state = false; //무한반복 탈출
			} else { //불러온 원두VO가 null일경우
				System.out.println("잘못된 원두 번호입니다"); 
			}
		}while(state);
		
		input.nextLine(); //nextint 줄바꿈 제거
		
		//원산지 수정
		System.out.print("원두 원산지 입력 [" + bean.getCountry() + "]  : ");
		bean.setCountry(input.nextLine());
		//지배지 수정
		System.out.print("원두 지배지 입력 [" + bean.getArea() + "]  : ");
		bean.setArea(input.nextLine());
		//농장 수정
		System.out.print("원두 농장 입력 [" + bean.getFarm() + "]  : ");
		bean.setFarm(input.nextLine());
		//농부 수정
		System.out.print("원두 농부 입력 [" + bean.getFarmer() + "]  : ");
		bean.setFarmer(input.nextLine());
		
		//해발고도 입력 숫자 입력할 때 까지 무한 반복
		while (true) {
			System.out.print("원두 해발고도 입력 [" + bean.getAltitude() + "]  : ");
			String value = input.nextLine(); //값 변수에 저장
			if (DBUtil.isInteger(value)) { //입력한 값 숫자인지 확인
				bean.setAltitude(Integer.parseInt(value)); //문자열 integer형으로 변환에서 입력
				break; //반복 탈출
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//품종 수정
		System.out.print("원두 품종 입력 [" + bean.getVariety() + "]  : ");
		bean.setVariety(input.nextLine());
		//가공방식 수정
		System.out.print("원두 가공방식 입력 [" + bean.getProcess() + "]  : ");
		bean.setProcess(input.nextLine());
		//볶음도 수정
		System.out.print("원두 볶음도 입력 [" + bean.getRoasting() + "]  : ");
		bean.setRoasting(input.nextLine());
		//향미 수정
		System.out.print("원두 향미 입력 [" + bean.getFlavor() + "]  : ");
		bean.setFlavor(input.nextLine());
		//판매가  숫자 입력할 때 까지 무한 반복
		while (true) {
			System.out.print("원두 판매가 입력 [" + bean.getPrice() + "]  : ");
			String value = input.nextLine();//값 변수에 저장
			if (DBUtil.isInteger(value)) {//입력한 값 숫자인지 확인
				bean.setPrice(Integer.parseInt(value)); //문자열 integer형으로 변환에서 입력
				break;//반복 탈출
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//용량 숫자 입력할 때 까지 무한 반복
		while (true) {
			System.out.print("원두 용량 입력 [" + bean.getVolume() + "]  : ");
			String value = input.nextLine();//값 변수에 저장
			if (DBUtil.isInteger(value)) {//입력한 값 숫자인지 확인
				bean.setVolume(Integer.parseInt(value));//문자열 integer형으로 변환에서 입력
				break;//반복 탈출
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//원가 숫자 입력할 때 까지 무한 반복
		while (true) {
			System.out.print("원두 원가 입력 [" + bean.getCost() + "]  : ");
			String value = input.nextLine();//값 변수에 저장
			if (DBUtil.isInteger(value)) {//입력한 값 숫자인지 확인
				bean.setCost(Integer.parseInt(value));//문자열 integer형으로 변환에서 입력
				break;//반복 탈출
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//제조일 날짜형 입력할 때 까지 무한 반복
		while (true) {
			System.out.print("원두 제조일 입력 [" + bean.getMakeDate() + "]  : ");
			String value = input.nextLine();//값 변수에 저장
			if (DBUtil.isDate(value)) {//입력한 값 날짜형식인지 확인
				bean.setMakeDate(value); 
				break; //반복 탈출
			} else {
				System.out.println("잘못된 날짜 형식입니다");
			}
		}

		bean.setState(1); //상태 1로 설정 1=표시 0=표시안함
		beanDAO.updateBean(bean); 
	}
	
	// 원두추가
	public void addBean() { 
		Scanner input = new Scanner(System.in);
		BeanVO bean = new BeanVO();
		
		while (true) { //원두 이름 중복체크
			System.out.print("원두 이름 입력 : ");
			String name = input.nextLine();
			if (beanDAO.checkSameName(name) == false) { //checkSameName(원두이름) 있으면 true 없으면 false
				bean.setName(name);
				break;
			} else {
				System.out.println("이미 있는 원두 이름입니다");
			}
		}
		//원두 원산지 입력
		System.out.print("원두 원산지 입력 : ");
		bean.setCountry(input.nextLine());
		// 원두 지배지 입력
		System.out.print("원두 지배지 입력 : ");
		bean.setArea(input.nextLine());
		//원두 농장 입력
		System.out.print("원두 농장 입력 : ");
		bean.setFarm(input.nextLine());
		//원두 농부 입력
		System.out.print("원두 농부 입력 : ");
		bean.setFarmer(input.nextLine());
		//원두 해발고도 숫자 입력검사
		while (true) {
			System.out.print("원두 해발고도 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) { //숫자 값인지 확인
 				bean.setAltitude(Integer.parseInt(value)); //문자열 숫자로 변환에서 입력
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//원두 품종입력
		System.out.print("원두 품종 입력 : ");
		bean.setVariety(input.nextLine());
		//가공방식 입력
		System.out.print("원두 가공방식 입력 : ");
		bean.setProcess(input.nextLine());
		//볶음도 입력
		System.out.print("원두 볶음도 입력 : ");
		bean.setRoasting(input.nextLine());
		//향미 입력
		System.out.print("원두 향미 입력 : ");
		bean.setFlavor(input.nextLine());
		//판매가 숫자 입력검사
		while (true) {
			System.out.print("원두 판매가 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {//숫자값인지 확인
				bean.setPrice(Integer.parseInt(value)); //문자열 숫자로 변환해서 입력
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//용량 숫자인지 검사
		while (true) {
			System.out.print("원두 용량g 입력(ex: 200) : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) { //숫자 값인지 확인
				bean.setVolume(Integer.parseInt(value)); //문자열 숫자로 변환해서 입력
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//원가 숫자인지 검사
		while (true) {
			System.out.print("원두 원가 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) { //숫자 값인지 확인
				bean.setCost(Integer.parseInt(value)); //문자열 숫자로 변환해서 입력
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		//제조일 날짜타입인지 검사
		while (true) {
			System.out.print("원두 제조일 입력 (ex 2023-12-01) : ");
			String value = input.nextLine();
			if (DBUtil.isDate(value)) { //입력받은 값 날짜형식인지 확인
				bean.setMakeDate(value);
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다");
			}
		}

		bean.setState(1);
		beanDAO.insertBean(bean); //입력받은 원두 정보 insert
	}
	//원두 삭제
	public void removeBean() {
		Scanner input = new Scanner(System.in);
		
		boolean state = true; //true면 반복 
		
		BeanVO bean = new BeanVO();
		
		//존재하는 원두 번호인지 검사
		do {
			beanDAO.selectAllBean(); //원두 전체목록
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt(); //입력받은 원두번호 저장
			bean = beanDAO.selectBean(beanId);  //원두번호로 원두정보 불러오기
			if (bean != null) { //원두정보가 null이 아닐 경우(존재)
				beanDAO.deleteBean(bean); //원두삭제
				state = false; //무한반복 탈출
			} else {
				System.out.println("잘못된 원두 번호입니다");
			}
		}while(state);


	}

	public void selectBean() { // 원두 상세정보
		Scanner input = new Scanner(System.in);

		
		BeanVO bean = new BeanVO();
		boolean state = true; //true이면 무한반복
		do {
			beanDAO.selectAllBean(); //원두 전체목록 불러오기
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt(); //원두 번호입력
			bean = beanDAO.selectBean(beanId); //입력한 원두번호로 원두 정보 불러오기
			
			if (bean != null) { //불러온 원두정보가 null이 아닐경우
				BeanVO beanVO = beanDAO.selectBean(beanId); // 원두를 원두번호로 불러오기
				System.out.println("────────────────────────────────────────────");
				System.out.println("일련번호: " + beanVO.getB_no());
				System.out.println("원두이름: " + beanVO.getName());
				System.out.println("원산지: " + beanVO.getCountry());
				System.out.println("지배지: " + beanVO.getArea());
				System.out.println("농장: " + beanVO.getFarm());

				System.out.println("농부: " + beanVO.getFarmer());
				System.out.println("해발고도: " + beanVO.getAltitude());
				System.out.println("품종: " + beanVO.getVariety());
				System.out.println("가공방식: " + beanVO.getProcess());
				System.out.println("볶음도: " + beanVO.getRoasting());

				System.out.println("향미: " + beanVO.getFlavor());
				System.out.println("용량: " + beanVO.getVolume());
				System.out.println("제조일: " + beanVO.getMakeDate());
				System.out.println("판매가: " + beanVO.getPrice());
				System.out.println("원가: " + beanVO.getCost());
				System.out.println("────────────────────────────────────────────");

				state = false; //무한 반복 탈출
			} else {
				System.out.println("잘못된 원두 번호입니다");
			}
		} while (state);

	}

}
