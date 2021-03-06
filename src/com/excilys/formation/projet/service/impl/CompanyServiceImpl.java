package com.excilys.formation.projet.service.impl;

import java.util.List;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.service.CompanyService;

public class CompanyServiceImpl implements CompanyService {
private CompanyDAO companyDAO;

public CompanyServiceImpl() {
	super();
	this.companyDAO = DAOFactory.INSTANCE_DAO.getCompanyDAO();
}

public CompanyServiceImpl(CompanyDAO companyDAO) {
	super();
	this.companyDAO = companyDAO;
}

/* (non-Javadoc)
 * @see com.excilys.formation.projet.service.CompanyService#getListeCompany()
 */
@Override
public List<Company> getListeCompany(){
	return companyDAO.getCompanies();
}
public CompanyDAO getCompanyDAO() {
	return companyDAO;
}

public void setCompanyDAO(CompanyDAO companyDAO) {
	this.companyDAO = companyDAO;
}

/* (non-Javadoc)
 * @see com.excilys.formation.projet.service.CompanyService#getCompanyNameById(long)
 */
@Override
public String getCompanyNameById(long id){
	return this.companyDAO.getById(id).getName();
}

}
