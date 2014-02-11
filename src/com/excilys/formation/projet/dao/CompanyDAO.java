package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.projet.om.Company;

public class CompanyDAO {

	public static Company getCompanyByName(String pName){
		Company company = new Company();
		ResultSet rs = null ;
		Statement stmt = null;
		Connection cn = null;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id,name FROM company WHERE name='"+pName+"';");
			while(rs.next()){
				company.setId(rs.getLong(1));
				company.setName(rs.getString(2));
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			try {
				if (rs != null)

					rs.close();

				if (stmt != null)

					stmt.close();

				if (cn != null) cn.close();
			} catch (SQLException e) {}
		}
		return company;
	}

	public Company getCompanyById(long id){
		Company c = new Company();
		ResultSet rs = null ;
		Statement stmt = null;
		Connection cn = null;
		try {

			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT name FROM company WHERE id='"+id+"';");
			while(rs.next()){
				c.setId(id);
				c.setName(rs.getString(1));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null)

					rs.close();

				if (stmt != null)

					stmt.close();

				if (cn != null) cn.close();
			} 
			catch (SQLException e) {}
		}
		return c;
	}
	public List<Company> getListeCompany(){
		List<Company> li = new ArrayList<Company>();
		ResultSet rs = null ;
		Statement stmt = null;
		Connection cn = null;
		try {

			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name FROM company;");
			li = getResultList(rs,cn);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null)

					rs.close();

				if (stmt != null)

					stmt.close();

				if (cn != null) cn.close();
			} catch (SQLException e) {}
		}

		return li;
	}

	public List<Company> getResultList(ResultSet rs, Connection cn){
		List<Company> list = new ArrayList<Company>();
		Company companyCourant = null;
		try {
			while(rs.next()){
				companyCourant = new Company(rs.getLong(1),rs.getString(2));
				list.add(companyCourant);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
