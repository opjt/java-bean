package controller;

import java.util.Scanner;

import model.BeanVO;
import model.CafeVO;

public class BeanController {
	static BeanDAO beanDAO = new BeanDAO();

	public void showList() { // 원두전체목록
		beanDAO.selectAllBean();

	}
	public void updateBean() {
		Scanner input = new Scanner(System.in);
		BeanVO bean = new BeanVO();
		
		boolean state = true;
		
		do {
			beanDAO.selectAllBean(); //원두 전체목록
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt();
			bean = beanDAO.selectBean(beanId);
			if (bean != null) {
				bean.setB_no(beanId);
				state = false;
			} else {
				System.out.println("잘못된 원두 번호입니다");
			}
		}while(state);
		input.nextLine();
		
		System.out.print("원두 원산지 입력 [" + bean.getCountry() + "]  : ");
		bean.setCountry(input.nextLine());

		System.out.print("원두 지배지 입력 [" + bean.getArea() + "]  : ");
		bean.setArea(input.nextLine());

		System.out.print("원두 농장 입력 [" + bean.getFarm() + "]  : ");
		bean.setFarm(input.nextLine());

		System.out.print("원두 농부 입력 [" + bean.getFarmer() + "]  : ");
		bean.setFarmer(input.nextLine());

		while (true) {
			System.out.print("원두 해발고도 입력 [" + bean.getAltitude() + "]  : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setAltitude(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}

		System.out.print("원두 품종 입력 [" + bean.getVariety() + "]  : ");
		bean.setVariety(input.nextLine());

		System.out.print("원두 가공방식 입력 [" + bean.getProcess() + "]  : ");
		bean.setProcess(input.nextLine());

		System.out.print("원두 볶음도 입력 [" + bean.getRoasting() + "]  : ");
		bean.setRoasting(input.nextLine());

		System.out.print("원두 향미 입력 [" + bean.getFlavor() + "]  : ");
		bean.setFlavor(input.nextLine());

		while (true) {
			System.out.print("원두 판매가 입력 [" + bean.getPrice() + "]  : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setPrice(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		while (true) {
			System.out.print("원두 용량 입력 [" + bean.getVolume() + "]  : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setVolume(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}

		while (true) {
			System.out.print("원두 원가 입력 [" + bean.getCost() + "]  : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setCost(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		while (true) {
			System.out.print("원두 제조일 입력 [" + bean.getMakeDate() + "]  : ");
			String value = input.nextLine();
			if (DBUtil.isDate(value)) {
				bean.setMakeDate(value);
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다");
			}
		}

		bean.setState(1);
		beanDAO.updateBean(bean);
	}
	public void addBean() { // 원두추가
		Scanner input = new Scanner(System.in);
		BeanVO bean = new BeanVO();

		while (true) {
			System.out.print("원두 이름 입력 : ");
			String name = input.nextLine();
			if (beanDAO.checkSameName(name) == false) {
				bean.setName(name);
				break;
			} else {
				System.out.println("이미 있는 원두 이름입니다");
			}
		}

		System.out.print("원두 원산지 입력 : ");
		bean.setCountry(input.nextLine());

		System.out.print("원두 지배지 입력 : ");
		bean.setArea(input.nextLine());

		System.out.print("원두 농장 입력 : ");
		bean.setFarm(input.nextLine());

		System.out.print("원두 농부 입력 : ");
		bean.setFarmer(input.nextLine());

		while (true) {
			System.out.print("원두 해발고도 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setAltitude(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}

		System.out.print("원두 품종 입력 : ");
		bean.setVariety(input.nextLine());

		System.out.print("원두 가공방식 입력 : ");
		bean.setProcess(input.nextLine());

		System.out.print("원두 볶음도 입력 : ");
		bean.setRoasting(input.nextLine());

		System.out.print("원두 향미 입력 : ");
		bean.setFlavor(input.nextLine());

		while (true) {
			System.out.print("원두 판매가 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setPrice(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		while (true) {
			System.out.print("원두 용량g 입력(ex: 200) : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setVolume(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}

		while (true) {
			System.out.print("원두 원가 입력 : ");
			String value = input.nextLine();
			if (DBUtil.isInteger(value)) {
				bean.setCost(Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		while (true) {
			System.out.print("원두 제조일 입력 (ex 2023-12-01) : ");
			String value = input.nextLine();
			if (DBUtil.isDate(value)) {
				bean.setMakeDate(value);
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다");
			}
		}

		bean.setState(1);
		beanDAO.insertBean(bean);
	}

	public void removeBean() { // 원두삭제
		Scanner input = new Scanner(System.in);
		boolean state = true;
		BeanVO bean = new BeanVO();
		do {
			beanDAO.selectAllBean(); //원두 전체목록
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt();
			bean = beanDAO.selectBean(beanId);
			if (bean != null) {
				beanDAO.deleteBean(bean);
				state = false;
			} else {
				System.out.println("잘못된 원두 번호입니다");
			}
		}while(state);


	}

	public void selectBean() { // 원두 상세정보
		Scanner input = new Scanner(System.in);

		
		BeanVO bean = new BeanVO();
		boolean state = true;
		do {
			beanDAO.selectAllBean();
			System.out.print("원두 번호 입력 : ");
			int beanId = input.nextInt();
			bean = beanDAO.selectBean(beanId);

			if (bean != null) {
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

				state = false;
			} else {
				System.out.println("잘못된 원두 번호입니다");
			}
		} while (state);

	}

}
