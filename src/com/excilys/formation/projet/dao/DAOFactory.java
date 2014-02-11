package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
// On fait un enum pour avoir une seule instance de DAOFactory
public enum DAOFactory {
	INSTANCE_DAO;
	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;
	
	private DAOFactory(){
		computerDAO = new ComputerDAO();
		companyDAO = new CompanyDAO();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
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

}
