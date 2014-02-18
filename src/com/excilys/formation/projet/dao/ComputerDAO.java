package com.excilys.formation.projet.dao;



import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.wrapper.PageWrapper;
/**
 * 
 * @author excilys
 *
 */
public interface ComputerDAO {
	/**
	 * Adds a computer in the database
	 * @param c computer to add
	 * @return
	 */
	public abstract int add(Computer c);
	/**
	 * Gets a computer in the database by its id
	 * @param id id of the computer
	 * @return
	 */
	public abstract Computer getById(long id);
	/**
	 * Removes a computer in the database 
	 * @param id id of the computer
	 * @return
	 */
	public abstract int delete(long id);
	/**
	 * Updates a computer in the database
	 * @param c
	 * @return
	 */
	public abstract int update(Computer c);

	/**
	 * Gets a list of computers in the database with a limit and an offset
	 * @param limit max amount of computers returned
	 * @param offset
	 * @return
	 */
	public abstract PageWrapper<Computer> getComputers(int limit, int offset);
	/**
	 * Gets a a list of computers in the database with a limit and an offset containing a particular String
	 * @param limit max amount of computers returned
	 * @param offset
	 * @param search
	 * @return
	 */
	public abstract PageWrapper<Computer> getComputers(int limit, int offset, String search);

}