package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import controller.AnimalController;
import exception.AnoNotExistException;
import model.dao.AnimalDAO;
import model.domain.Animal;
import util.ScreenClear;
import util.SoundPlayer;


public class AnimalStartView {
	private static Scanner sc;
	public static void main(String[] args) {
		SoundPlayer sp = new SoundPlayer();
		AnimalController.deleteAll();
		AnimalController.insert(new Animal(1, "꾸구리", "어류", "2급", "물의 흐름이 빠르고 자갈이 깔린 하천의 상류에 서식하며 수서곤충을 섭식한다."));
		AnimalController.insert(new Animal(2, "날개하늘나리", "육상식물", "2급", "강원도 오대산, 설악산, 태백산, 대암산 등 강원도 이북 지방에 주로 분포한다"));
		AnimalController.insert(new Animal(3, "닻무늬앞잡이", "곤충류", "2급", "몸길이 12-15mm이고, 가슴의 등면은 갈색과 녹색을 띠고 있다.)"));
		AnimalController.insert(new Animal(4, "모래주사", "어류", "2급", "체장은 10-12cm이며, 체형은 가늘고 길며 옆으로 약간 납작하다."));
		AnimalController.insert(new Animal(5, "밤수지맨드라미", "무척추동물", "2급", "우산형 군체. 군체는 약간 납작하고 작다"));
		AnimalController.insert(new Animal(6, "암매", "육상식물", "1급", "상록성 관목으로 높이 5cm 정도이고, 방석처럼 모여 자란다. "));
		AnimalController.insert(new Animal(7, "여울마자", "어류", "1급", "서식지 현황: 하천 중·하류의 모래와 자갈이 섞인 여울부의 아래쪽 유속이 빠른 곳에서 산다. "));
		AnimalController.insert(new Animal(8, "꼬치동자개", "어류", "1급", "물이 맑고 자갈과 큰돌이 있는 하천의 상류에 서식한다."));
		AnimalController.insert(new Animal(9, "느시", "조류", "2급", "광활한 평야, 건조한 구릉의 초지, 초원 등지에 서식한다"));
		AnimalController.insert(new Animal(10, "광릉요강꽃", "육상식물", "1급", "해발 300-1,100m 산지 사면의 햇빛이 들며 배수가 양호한 음지에서 자란다."));
		screenMain();
	}
	public static void screenMain(){
		ScreenClear.screenClear();
		System.out.println("********************* KORED ***************");
		System.out.println("*****코리드는 멸종희귀 동물을 사랑합니다.******");
		System.out.println("*** 1. 전체 검색		***");
		System.out.println("*** 2. 이름으로 검색	***");
		System.out.println("*** 3. 멸종동물 입력 	***");
		System.out.println("*** 4. 기존 동물 멸종 	***");
		System.out.println("*** 5. 종료 		***");
		System.out.print("*** 선택 : ");
		int select = 0;
		sc = new Scanner(System.in);
		String s = sc.nextLine();
		select = Integer.parseInt(s);
		
		switch (select) {
		case 1:
			ScreenClear.screenClear();
			screenSub();
			break;
		case 2:
			System.out.print("*** 이름 : ");
			s = sc.nextLine();
			try {
				Animal animal = AnimalDAO.searchByaName(s);
				if(animal != null){
					AnimalDetailView At = new AnimalDetailView(animal);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (AnoNotExistException e) {
				e.printStackTrace();
			}
			ScreenClear.screenClear();
			screenMain();
			break;
		case 3:
			AnimalDetailView newAt = new AnimalDetailView(new Animal());
			ScreenClear.screenClear();
			screenMain();
			break;
		case 4:
			ScreenClear.screenClear();
			System.out.println("********************* KORED ***************");
			AnimalController.getAnimalAll();
			System.out.println("******************************************");
			System.out.print("*** 멸종된 동물 번호 : ");
			s = sc.nextLine();			
			AnimalController.delete(Integer.parseInt(s));
			System.out.println("*** 멸종되었습니다. 처음으로 되돌아 가시겠습니까?");
			System.out.println("*** 1. 처음으로 가기");
			System.out.println("*** 2. 종료");
			
			do {
				System.out.print("*** 선택 : ");
				s = sc.nextLine();
				if(s.equals("1")){
					ScreenClear.screenClear();
					screenMain();
				}else if(s.equals("2")){
					ScreenClear.screenClear();
				}else{
					System.out.println("*** 다시 입력해 주세요");
				}
				
			} while (s.equals("1") || s.equals("2") );
			
			break;
		case 5:
			ScreenClear.screenClear();
			break;

		default:
			ScreenClear.screenClear();
			screenMain();
			break;
		}
	}
	
	public static void screenSub(){
		
		System.out.println("********************* KORED ***************");
		AnimalController.getAnimalAll();
		
		System.out.println("******************************************");
		System.out.println("*** 1. 상세 보기		***");
		System.out.println("*** 2. 뒤로 가기		***");
		System.out.println("*** 3. 종료 			***");
		System.out.print("*** 선택 : ");
		
		
		int select = 0;
		sc = new Scanner(System.in);
		String s = sc.nextLine();
		select = Integer.parseInt(s);
		
		switch (select) {
		case 1:
			System.out.print("*** 이름 : ");
			s = sc.nextLine();
			
			try {
				Animal animal = AnimalDAO.searchByaName(s);
				if(animal != null){
					AnimalDetailView At = new AnimalDetailView(animal);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (AnoNotExistException e) {
				e.printStackTrace();
			}
			ScreenClear.screenClear();
			screenMain();
			break;
		case 2:
			ScreenClear.screenClear();
			screenMain();
			break;
		case 3:
			ScreenClear.screenClear();
			break;
		

		default:
			ScreenClear.screenClear();
			screenSub();
			break;
		}
		
	}
}
