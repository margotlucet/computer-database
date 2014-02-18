package com.excilys.formation.projet.service.impl;


import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.Computer;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.wrapper.PageWrapper;

public class ComputerServiceImpl implements ComputerService {
	private ComputerDAO computerDAO;
	public ComputerServiceImpl() {
		super();
		this.computerDAO = DAOFactory.INSTANCE_DAO.getComputerDAO();
	}

	public ComputerServiceImpl(ComputerDAO computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#getComputerById(long)
	 */
	@Override
	public Computer getById(long id){
		Computer c = this.computerDAO.getById(id);
		return c;
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#addComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public void add(Computer c){	
		computerDAO.add(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#editComputer(com.excilys.formation.projet.om.Computer)
	 */
	@Override
	public void update(Computer c){
		computerDAO.update(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.formation.projet.service.ComputerService#deleteComputer(java.lang.String)
	 */
	@Override
	public void delete(String id){
		computerDAO.delete(Long.parseLong(id));
	}
	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}


	@Override
	public PageWrapper<Computer> getPage(int page, int nbResult) {	

		return this.computerDAO.getComputers(nbResult, (page-1)*nbResult);
	}

	@Override
	public PageWrapper<Computer> getPage(int page, int nbResult, String search) {
		return this.computerDAO.getComputers(nbResult, (page-1)*nbResult, search);
	}


}
