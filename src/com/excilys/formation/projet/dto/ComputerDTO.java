package com.excilys.formation.projet.dto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.excilys.formation.projet.dao.CompanyDAO;
import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.om.*;

public class ComputerDTO {
	private long id = 0;
	private String name;
	private String introduced;
	private String discontinued;
	private String company;

	public ComputerDTO(Computer c){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.id = c.getId();
		this.name = c.getName();
		Date dIntroduced = c.getIntroduced();
		Date dDiscontinued = c.getDiscontinued();
		if(dIntroduced!=null)
			this.introduced = formatter.format(c.getIntroduced());
		else
			this.introduced = "unknown";
		if(dDiscontinued!=null)
			this.discontinued = formatter.format(c.getDiscontinued());
		else
			this.discontinued = "unknown";
		this.company = c.getCompany().getName();

	}



	public ComputerDTO(String name, String introduced,
			String discontinued, String company) {
		super();
		this.name = name;
		if(!introduced.equals(""))
			this.introduced = introduced;
		else
			this.introduced = "unknown";
		if(!discontinued.equals(""))
			this.discontinued = discontinued;
		else
			this.discontinued = "unknown";
		this.company = company;
	}



	public Computer fromDTOtoComputer(){
		Company c = CompanyDAO.getCompanyByName(this.getCompany());
		Computer computer = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		try {
			if(!this.introduced.equals("unknown")){
				if(!this.discontinued.equals("unknown")){
					computer = new Computer(this.id, this.name, formatter.parse(this.introduced), formatter.parse(this.discontinued), c);
				}
				else {
					computer = new Computer(this.id, this.name, formatter.parse(this.introduced), null, c);
				}
			}
			else if(!this.discontinued.equals("unknown")){
				computer = new Computer(this.id, this.name, null, formatter.parse(this.discontinued), c);
			}
			else{
				computer = new Computer(this.id, this.name, null, null,c);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}



	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}



}
