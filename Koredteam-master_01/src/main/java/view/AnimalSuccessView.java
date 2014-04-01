package view;

import java.util.ArrayList;

import model.domain.Animal;

public class AnimalSuccessView {
	public static void successMsg(String msg) {
		System.out.println(msg);
	}
	public static void printAll(ArrayList<Animal> all) {
		int count = all.size();
		if(count != 0){
			for(int index=0; index < count;index++){
				System.out.println(all.get(index));
			}
		}else{
			System.out.println("등록된 멸종위기동물 정보가 없습니다.");
		}
	}
	
	
	public static void printAnimal(Animal a) {
		if(a != null){
			System.out.println(a);
		}else{
			System.out.println("검색하고자 하는 멸종위기동물 정보가 없습니다");
		}
	}
}
