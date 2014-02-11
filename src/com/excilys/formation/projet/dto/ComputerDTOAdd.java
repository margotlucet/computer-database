package com.excilys.formation.projet.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.excilys.formation.projet.om.Company;
import com.excilys.formation.projet.om.Computer;

public class ComputerDTOAdd {
	private String name;
	private String introduced;
	private String discontinued;
	private long company;
	
	
	public ComputerDTOAdd(String name, String introduced, String discontinued,
			String company) {
		super();
		this.name = name;
		if(!introduced.equals(""))
			this.introduced = introduced;
		else
			this.introduced = null;
		if(!discontinued.equals(""))
			this.discontinued = discontinued;
		else
			this.discontinued = null;
		this.company = Long.parseLong(company);
	}
	
	public Computer dtoToComputer(){
		Computer computer = new Computer();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		try {
			if(this.introduced!=null){
				if(this.discontinued!=null){
					computer = new Computer(0, this.name, formatter.parse(this.introduced), formatter.parse(this.discontinued), new Company(this.company,null));
				}
				else {
					computer = new Computer(0, this.name, formatter.parse(this.introduced), null, new Company(this.company,null));
				}
			}
			else if(this.discontinued!=null){
				computer = new Computer(0, this.name, null, formatter.parse(this.discontinued), new Company(this.company,null));
			}
			else{
				computer = new Computer(0, this.name, null, null,new Company(this.company,null));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
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
	public long getCompany() {
		return company;
	}
	public void setCompany(long company) {
		this.company = company;
	}
}
