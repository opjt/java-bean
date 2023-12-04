package controller;

import java.util.Scanner;

import model.BeanVO;
import model.CafeVO;

public class BeanController {
	static BeanDAO beanDAO = new BeanDAO();
	
	public void showList() {
		beanDAO.selectAllBean();
		
	}

	public void addBean() {
		Scanner input = new Scanner(System.in);
		BeanVO bean = new BeanVO();
		
		while(true) {
			System.out.print("원두 이름 입력 : ");
			String name = input.nextLine();
			if(beanDAO.checkSameName(name) == false) {
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
		
		while(true) {
			System.out.print("원두 해발고도 입력 : ");
			String value = input.nextLine();
			if(DBUtil.isInteger(value)) {
				bean.setAltitude( Integer.parseInt(value));
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
		
		while(true) {
			System.out.print("원두 판매가 입력 : ");
			String value = input.nextLine();
			if(DBUtil.isInteger(value)) {
				bean.setPrice( Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		
		while(true) {
			System.out.print("원두 원가 입력 : ");
			String value = input.nextLine();
			if(DBUtil.isInteger(value)) {
				bean.setCost( Integer.parseInt(value));
				break;
			} else {
				System.out.println("숫자를 입력하시오");
			}
		}
		while(true) {
			System.out.print("원두 제조일 입력 (ex 2023-12-01) : ");
			String value = input.nextLine();
			if(DBUtil.isDate(value)) {
				bean.setMakeDate(value);
				break;
			} else {
				System.out.println("잘못된 날짜 형식입니다");
			}
		}
		
		bean.setState(1);
		beanDAO.insertBean(bean);
	}

	public void removeBean() {
		Scanner input = new Scanner(System.in);
		
		beanDAO.selectAllBean();
		
		System.out.print("원두 번호 입력 : ");
		beanDAO.deleteBean(beanDAO.selectBean(input.nextInt()));
	
		
	}

	public void selectBean() {
		Scanner input = new Scanner(System.in);
		
		beanDAO.selectAllBean();
		
		System.out.print("원두 번호 입력 : ");
		
		
		
		BeanVO beanVO = beanDAO.selectBean(input.nextInt());
		System.out.println("일련번호\t\t원두이름\t\t원산지\t\t지배지\t\t농장");
		System.out.println(beanVO.getB_no() + "\t\t" + beanVO.getName() + "\t\t" + beanVO.getCountry() +"\t\t" + beanVO.getArea()
		+"\t\t" + beanVO.getFarm());
		System.out.println("농부\t\t해발고도\t\t품종\t\t가공방식\t\t볶음도");
		System.out.println(beanVO.getFarmer() + "\t\t" + beanVO.getAltitude() + "\t\t" + beanVO.getVariety() +"\t\t" + beanVO.getProcess() + 
		"\t\t" + beanVO.getRoasting());
		System.out.println("향미\t\t용량\t\t제조일\t\t판매가\t\t원가");
		System.out.println(beanVO.getFlavor() + "\t\t" + beanVO.getVolume() + "\t\t" + beanVO.getMakeDate() + "\t\t" + beanVO.getPrice() 
		+ "\t\t" + beanVO.getCost());
	
		
	}

}
