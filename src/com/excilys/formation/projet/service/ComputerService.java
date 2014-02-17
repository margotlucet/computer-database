package com.excilys.formation.projet.service;

import java.util.List;

import com.excilys.formation.projet.om.Computer;

public interface ComputerService {

	public abstract Computer getById(long id);

	public abstract List<Computer> getComputers();

	public abstract List<Computer> getComputers(String search);

	public abstract void add(Computer c);

	public abstract void update(Computer c);

	public abstract void delete(String id);

	public abstract int getNumber();
	
	public abstract int getNumber(String search);
	
	public abstract List<Computer> getPage(int page, int nbResult);
	
	public abstract List<Computer> getPage(int page, int nbResult, String search);
}