package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.excilys.formation.projet.dao.impl.CompanyDAOImpl;
import com.excilys.formation.projet.dao.impl.ComputerDAOImpl;
// On fait un enum pour avoir une seule instance de DAOFactor
/**
 * Factory of DAO
 * @author excilys
 *
 */
public enum DAOFactory {
	INSTANCE_DAO;
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	
	private DAOFactory(){
		computerDAO = new ComputerDAOImpl();
		companyDAO = new CompanyDAOImpl();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Gets a connection to the database
	 * @return a new connection
	 */
	public Connection getConnexion(){
		Connection cn = null;

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull","root","apgjtouirj");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}


	public CompanyDAO getCompanyDAO() {
		return companyDAO;
	}
	
	public void closeConnection(ResultSet rs, Connection cn, PreparedStatement ps){
		
	}

}
