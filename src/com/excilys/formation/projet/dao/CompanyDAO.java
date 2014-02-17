package com.excilys.formation.projet.dao;

import java.util.List;

import com.excilys.formation.projet.om.Company;

public interface CompanyDAO {

	public abstract Company getById(long id);

	public abstract List<Company> getCompanies();

}