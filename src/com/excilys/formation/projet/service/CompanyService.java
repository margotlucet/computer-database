package com.excilys.formation.projet.service;

import java.util.List;

import com.excilys.formation.projet.om.Company;

public interface CompanyService {

	public abstract List<Company> getListeCompany();

	public abstract String getCompanyNameById(long id);

}