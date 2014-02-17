package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;


public class CompanyDAOImpl implements CompanyDAO {
	public static Company getCompanyByName(String pName){
		Company company = new Company.Builder().build();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id,name FROM company WHERE name=?;");
			stmt.setString(1,pName);
			rs = stmt.executeQuery();
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

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return company;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.CompanyDAO#getCompanyById(long)
	 */
	@Override
	public Company getById(long id){
		Company c = new Company.Builder().build();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {

			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT name FROM company WHERE id=?;");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				c.setId(id);
				c.setName(rs.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null)

					rs.close();

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.CompanyDAO#getListeCompany()
	 */
	@Override
	public List<Company> getCompanies(){
		List<Company> li = new ArrayList<Company>();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {

			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name FROM company;");
			rs = stmt.executeQuery();
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

				if (cn != null) 

					cn.close();

			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return li;
	}

	public List<Company> getResultList(ResultSet rs, Connection cn){
		List<Company> list = new ArrayList<Company>();
		Company companyCourant = null;
		try {
			while(rs.next()){
				companyCourant = new Company.Builder().id(rs.getLong(1)).name(rs.getString(2)).build();
				list.add(companyCourant);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally{

			try {
				if(rs!=null)
					rs.close();
				if(cn!=null)
					cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}
}
