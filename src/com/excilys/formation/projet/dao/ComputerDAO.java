package com.excilys.formation.projet.dao;

import java.util.List;

import com.excilys.formation.projet.om.Computer;

public interface ComputerDAO {

	public abstract int add(Computer c);

	public abstract Computer getById(long id);

	public abstract int delete(long id);

	public abstract int update(Computer c);
	
	public abstract int getNumber();
	
	public abstract int getNumber(String search);
	
	public abstract List<Computer> getComputers();
	
	public abstract List<Computer> getComputers(String search);
	
	public abstract List<Computer> getComputers(int limit, int offset);
	
	public abstract List<Computer> getComputers(int limit, int offset, String search);

}