package com.excilys.formation.projet.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.projet.dao.ComputerDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.dto.ComputerDTOAdd;
import com.excilys.formation.projet.om.Computer;

public class ComputerService {
	private ComputerDAO computerDAO;
	public ComputerService() {
		super();
		this.computerDAO = DAOFactory.INSTANCE_DAO.getComputerDAO();
	}

	public ComputerService(ComputerDAO computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}

	public ComputerDTO getComputerById(String id){
		ComputerDTO cdto = new ComputerDTO(this.computerDAO.getComputerById(Long.parseLong(id)));
		return cdto;
	}
	public List<ComputerDTO> getListComputer(){
		List<Computer> resultList = computerDAO.getListComputer();
		return listComputerToListComputerDTO(resultList);
	}

	public List<ComputerDTO> getListComputer(String search){
		List<Computer> resultList = computerDAO.getListComputer(search);
		return listComputerToListComputerDTO(resultList);
	}

	public List<ComputerDTO> listComputerToListComputerDTO(List<Computer> list){
		List<ComputerDTO> formattedResultList = new ArrayList<ComputerDTO>();
		for(Computer c : list){
			formattedResultList.add(new ComputerDTO(c));
		}
		return formattedResultList;
	}
	public void addComputer(ComputerDTOAdd c){	
		computerDAO.addComputer(c.dtoToComputer());
	}

	public void editComputer(ComputerDTOAdd c, String id){
		Computer computer = c.dtoToComputer();	
		computer.setId(Long.parseLong(id));
		computerDAO.editComputer(computer);
	}
	
	public void deleteComputer(String id){
		computerDAO.deleteComputer(Long.parseLong(id));
	}
	public ComputerDAO getComputerDAO() {
		return computerDAO;
	}

	public void setComputerDAO(ComputerDAO computerDAO) {
		this.computerDAO = computerDAO;
	}


}
