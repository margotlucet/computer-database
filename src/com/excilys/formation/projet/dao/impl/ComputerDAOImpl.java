package com.excilys.formation.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;

public class ComputerDAOImpl implements ComputerDAO {


	public List<Computer> getComputers(){
		List<Computer> li = new ArrayList<Computer>();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name ,introduced, discontinued, company_id FROM computer;");
			rs = stmt.executeQuery();
			li = this.getResultList(rs, cn);
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

		return li;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.ComputerDAO#addComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public int add(Computer c){
		int result = 0;
		PreparedStatement stmt = null;
		Connection cn = null;
		long idCompany;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)");
			stmt.setString(1, c.getName());

			if(c.getIntroduced()!=null)
				stmt.setTimestamp(2, new Timestamp(c.getIntroduced().getTime()));
			else 
				stmt.setTimestamp(2, null);
			if(c.getDiscontinued()!=null)
				stmt.setTimestamp(3, new Timestamp(c.getDiscontinued().getTime()));
			else
				stmt.setTimestamp(3, null);
			idCompany = c.getCompany().getId();
			if(idCompany!=0)
				stmt.setLong(4, idCompany);
			else
				stmt.setNull(4, Types.BIGINT);
			System.out.println(stmt);
			result=stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (stmt != null)

					stmt.close();

				if (cn != null)

					cn.close(); 

			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public List<Computer> getComputers(String search){
		List<Computer> li = new ArrayList<Computer>();
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name ,introduced, discontinued, company_id FROM computer WHERE name LIKE ?;");
			stmt.setString(1, "%"+search+"%");
			rs = stmt.executeQuery();
			li = this.getResultList(rs, cn);
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

		return li;
	}
	public List<Computer> getResultList(ResultSet rs, Connection cn){
		List<Computer> list = new ArrayList<Computer>();
		Computer computerCourant = null;
		PreparedStatement stmtCompany = null;
		String name = "";
		long id = 0;
		ResultSet rsCompany = null;
		try{
			while(rs.next()){
				computerCourant = new Computer.Builder().id(rs.getLong(1)).name(rs.getString(2)).introduced(rs.getTimestamp(3))
						.discontinued(rs.getTimestamp(4)).build();
				id = rs.getLong(5);
				if(id!=0){
					stmtCompany = cn.prepareStatement("SELECT name FROM company WHERE id=?;");
					stmtCompany.setLong(1, id);
					rsCompany = stmtCompany.executeQuery();
					while(rsCompany.next()){
						name = rsCompany.getString(1);
					}
					computerCourant.setCompany(new Company.Builder().id(id).name(name).build());
				}
				else{
					computerCourant.setCompany(new Company.Builder().build());
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
				if(rs != null)
					rs.close();
				if(cn != null)
					cn.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.ComputerDAO#getComputerById(long)
	 */
	@Override
	public Computer getById(long id){
		Computer computer = null;
		ResultSet rs = null ;
		PreparedStatement stmt = null;
		Connection cn = null;
		List<Computer> li = null;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name ,introduced, discontinued, company_id FROM computer WHERE id=?;");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			li = this.getResultList(rs, cn);
			computer = li.get(0);
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

				if (cn != null) cn.close(); 
			} catch (SQLException e) {}
		}

		return computer;
	}
	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.ComputerDAO#deleteComputer(long)
	 */
	@Override
	public int delete(long id){
		int result = 0;
		PreparedStatement stmt = null;
		Connection cn = null;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("DELETE FROM computer WHERE id=?");
			stmt.setLong(1, id);
			result = stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close(); 

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.dao.impl.ComputerDAO#editComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public int update(Computer c){
		int result = 0;
		PreparedStatement stmt = null;
		Connection cn = null;
		long idCompany = 0;
		try {
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;");
			stmt.setString(1, c.getName());
			if(c.getIntroduced()!=null)
				stmt.setTimestamp(2, new Timestamp(c.getIntroduced().getTime()));
			else 
				stmt.setTimestamp(2, null);
			if(c.getDiscontinued()!=null)
				stmt.setTimestamp(3, new Timestamp(c.getDiscontinued().getTime()));
			else
				stmt.setTimestamp(3, null);
			idCompany = c.getCompany().getId();
			if(idCompany!=0)
				stmt.setLong(4, idCompany);
			else
				stmt.setNull(4, Types.BIGINT);
			stmt.setLong(5, c.getId());
			result = stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int getNumber() {
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		int number = 0;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT COUNT(*) FROM computer;");
			rs = stmt.executeQuery();
			while(rs.next()){
				number = rs.getInt(1);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}

	@Override
	public List<Computer> getComputers(int limit, int offset) {
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		List<Computer> li = null;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name ,introduced, discontinued, company_id FROM computer LIMIT ? OFFSET ?;");
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);
			rs = stmt.executeQuery();
			li = this.getResultList(rs, cn);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try {

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return li;
	}

	@Override
	public List<Computer> getComputers(int limit, int offset, String search) {
		List<Computer> li = null;
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT id, name ,introduced, discontinued, company_id FROM computer WHERE name LIKE ? LIMIT ? OFFSET ? ;");
			stmt.setInt(2, limit);
			stmt.setInt(3, offset);
			stmt.setString(1, "%"+search+"%");
			rs = stmt.executeQuery();
			li = this.getResultList(rs, cn);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try {

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return li;
	}

	@Override
	public int getNumber(String search) {
		PreparedStatement stmt = null;
		Connection cn = null;
		ResultSet rs = null;
		int number = 0;
		try{
			cn = DAOFactory.INSTANCE_DAO.getConnexion();
			stmt = cn.prepareStatement("SELECT COUNT(*) FROM computer WHERE name LIKE ?;");
			stmt.setString(1,"%"+search+"%");
			rs = stmt.executeQuery();
			while(rs.next()){
				number = rs.getInt(1);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try {

				if (stmt != null)

					stmt.close();

				if (cn != null) 

					cn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return number;
	}

}
