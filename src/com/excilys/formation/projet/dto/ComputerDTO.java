package com.excilys.formation.projet.dto;



import java.text.SimpleDateFormat;
import java.util.Date;

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
