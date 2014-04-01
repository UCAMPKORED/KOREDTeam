package controller;

import java.sql.SQLException;

import model.dao.AnimalDAO;
import model.domain.Animal;
import view.AnimalFailView;
import view.AnimalSuccessView;
import exception.AnoDoubleException;
import exception.AnoNotExistException;

public class AnimalController {
	// insert로직
	public static void insert(Animal animal) {
		try {

			if (AnimalDAO.insert(animal)) {
				AnimalSuccessView.successMsg(animal.getaName() + "등록 성공");
			} else {
				AnimalFailView.failMsg(animal.getaName() + "등록 실패");
			}

		} catch (AnoDoubleException e) {
			e.printStackTrace();
			AnimalFailView.failMsg(e.getMessage());// exception 객체 생성시의 메세지값을 반환
		} catch (SQLException e) {
			e.printStackTrace();
			AnimalFailView.failMsg("등록 실패 다시 시도하세요");
		}
	}

	// 모든 데이터 검색하는 로직
	public static void getAnimalAll() {
		try {
			AnimalSuccessView.printAll(AnimalDAO.getAnimalAll());
		} catch (SQLException s) {
			s.printStackTrace();
			AnimalFailView.failMsg("모든 검색 실패");
		}
	}

	public static void searchByName(String aname) {
		try {
			AnimalSuccessView.printAnimal(AnimalDAO.searchByaName(aname));
		} catch (AnoNotExistException e) {
			e.printStackTrace();
			AnimalFailView.failMsg(e.getMessage());
		} catch (SQLException s) {
			s.printStackTrace();
			AnimalFailView.failMsg(aname + " 검색 실패");
		}
	}

	public static void update(Animal animal) {
		try {
			if (AnimalDAO.update(animal)){
				AnimalSuccessView.successMsg("동물 정보 변경 성공");
			} else {
				AnimalFailView.failMsg("동물 정보 변경 실패");
			}
		} catch (AnoNotExistException e) {
			e.printStackTrace();
			AnimalFailView.failMsg(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			AnimalFailView.failMsg("동물 정보 변경 실패 다시 시도하세요");
		}
	}


	public static void delete(int ano) {
		try {
			if (AnimalDAO.delete(ano)) {
				AnimalSuccessView.successMsg(ano + "동물 정보 삭제 성공");
			} else {
				AnimalFailView.failMsg(ano + "동물 정보 삭제 실패");
			}
		} catch (AnoNotExistException e) {
			e.printStackTrace();
			AnimalFailView.failMsg(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			AnimalFailView.failMsg(ano + "동물 정보 삭제 실패 다시 시도하세요");
		}
	}

	public static void deleteAll() {
		try {
			if (AnimalDAO.deleteAll()) {
				AnimalSuccessView.successMsg("전체 삭제 성공");
			} else {
				AnimalFailView.failMsg("전체 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			AnimalFailView.failMsg("전체 삭제 실패 다시 시도하세요");
		}
	}

}
