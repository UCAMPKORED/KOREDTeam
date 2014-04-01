package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.domain.Animal;

import org.apache.ibatis.session.SqlSession;

import util.DAOFactory;
import exception.AnoDoubleException;
import exception.AnoNotExistException;

public class AnimalDAO {

	public static ArrayList<Animal> getAnimalAll() throws SQLException {
		
		SqlSession session = DAOFactory.getSqlSession();
		ArrayList<Animal> selectAll = null;
		try {
			selectAll = (ArrayList)session.selectList("Kored.selectAllAnimal");
		} finally{
			session.close();			
		}
		return selectAll;
		
	}

	public static Animal searchByaName(String aname) throws SQLException, AnoNotExistException{
		SqlSession session = DAOFactory.getSqlSession();
		Animal animal = null;
		try {
			animal = (Animal)session.selectOne("Kored.selectAnimalByAname", aname);
		} finally{
			session.close();			
		}
		return animal;
		
	}

	public static boolean insert(Animal animal) throws SQLException,AnoDoubleException{
			
		SqlSession session = DAOFactory.getSqlSession(true);
		try {
			return (session.insert("Kored.insertAnimal", animal) >= 1)? true:false;
		} finally{
			session.close();			
		}

	}


	public static boolean update(Animal animal) throws SQLException, AnoNotExistException{
			
		SqlSession session = DAOFactory.getSqlSession(true);
		try {
			return  (session.update("Kored.updateAnimal", animal) >= 1) ? true:false;
		} finally{
			session.close();			
		}
	
	}

	

	public static boolean delete(int ano) throws SQLException, AnoNotExistException{
	
		SqlSession session = DAOFactory.getSqlSession(true);
		try {
			return (session.delete("Kored.deleteAnimalByAno", new Integer(ano)) >= 1) ? true:false;
		} finally{
			session.close();
		}
			
	}
	

	public static boolean deleteAll() throws SQLException {
		
		SqlSession session = DAOFactory.getSqlSession(true);
		try {
			return (session.delete("Kored.deleteAnimal") >= 1) ? true:false;
		} finally{
			session.close();
		}
		
	}

}