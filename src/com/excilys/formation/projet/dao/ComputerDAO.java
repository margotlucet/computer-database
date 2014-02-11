package com.excilys.formation.projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;

public class ComputerDAO {



	public Computer getComputer(long id){
		return null;
	}
	public List<Computer> getListComputer(){
		List<Computer> li = new ArrayList<Computer>();
		ResultSet rs = null ;
		Statement stmt = null;
		Connection cn = null;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name ,introduced, discontinued, company_id FROM computer;");
			li = this.getResultList(rs, cn);
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

	public void addComputer(Computer c){
		Statement stmt = null;
		Connection cn = null;
		StringBuilder requete = new StringBuilder();
		String insertIntroduced = "";
		String insertDiscontinued = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			requete.append("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ('");
			requete.append(c.getName());
			requete.append("', ");
			if(c.getIntroduced()!=null)
				insertIntroduced = "'"+formatter.format(c.getIntroduced())+" 00:00:00', ";
			else 
				insertIntroduced = "null, ";
			requete.append(insertIntroduced);
			
			if(c.getDiscontinued()!=null)
				insertDiscontinued = "'"+formatter.format(c.getDiscontinued())+" 00:00:00', ";
			else
				insertDiscontinued = "null, ";
			requete.append(insertDiscontinued);
			requete.append(c.getCompany().getId());
			requete.append(");");
			stmt.executeUpdate(requete.toString());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Computer> getListComputer(String search){
		List<Computer> li = new ArrayList<Computer>();
		ResultSet rs = null ;
		Statement stmt = null;
		Connection cn = null;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.createStatement();
			rs = stmt.executeQuery("SELECT id, name ,introduced, discontinued, company_id FROM computer WHERE name LIKE '%"+search+"%';");
			li = this.getResultList(rs, cn);
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
	public List<Computer> getResultList(ResultSet rs, Connection cn){
		List<Computer> list = new ArrayList<Computer>();
		Computer computerCourant = null;
		Statement stmtCompany = null;
		String name = "";
		long id = 0;
		ResultSet rsCompany = null;
		try{
			while(rs.next()){
				computerCourant = new Computer();
				computerCourant.setId(rs.getLong(1));
				computerCourant.setName(rs.getString(2));
				computerCourant.setIntroduced(rs.getTimestamp(3));
				computerCourant.setDiscontinued((rs.getTimestamp(4)));
				id = rs.getLong(5);
				if(id!=0){
					stmtCompany = cn.createStatement();
					rsCompany = stmtCompany.executeQuery("SELECT name FROM company WHERE id="+id+";");
					while(rsCompany.next()){
						name = rsCompany.getString(1);
					}
					computerCourant.setCompany(new Company(id,name));
				}
				else{
					computerCourant.setCompany(new Company());
				}
				list.add(computerCourant);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try{
				if(rsCompany != null)
					rsCompany.close();
				if(stmtCompany != null)
					stmtCompany.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
}
