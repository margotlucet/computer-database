package com.excilys.formation.projet.service;

import java.util.List;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;

public class CompanyService {
private CompanyDAO companyDAO;

public CompanyService() {
	super();
	this.companyDAO = DAOFactory.INSTANCE_DAO.getCompanyDAO();
}

public CompanyService(CompanyDAO companyDAO) {
	super();
	this.companyDAO = companyDAO;
}

public List<Company> getListeCompany(){
	return companyDAO.getListeCompany();
}
public CompanyDAO getCompanyDAO() {
	return companyDAO;
}

public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
}

public String getCompanyNameById(long id){
	return this.companyDAO.getCompanyById(id).getName();
}

}
