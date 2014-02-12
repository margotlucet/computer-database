package com.excilys.formation.projet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.dto.ComputerDTOAdd;
import com.excilys.formation.projet.service.ComputerService;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerService cs = new ComputerService();
		ComputerDTO cdto = cs.getComputerById(request.getParameter("id"));
		request.setAttribute("computer", cdto);
		getServletContext().getRequestDispatcher("/WEB-INF/view/editComputer.jsp").forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerDTOAdd cDTO = new ComputerDTOAdd(request.getParameter("name"), request.getParameter("introducedDate"), 
				request.getParameter("discontinuedDate"), request.getParameter("company"));
		ComputerService computerService = new ComputerService(DAOFactory.INSTANCE_DAO.getComputerDAO());
		computerService.editComputer(cDTO, request.getParameter("id"));
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);	
	}

}
