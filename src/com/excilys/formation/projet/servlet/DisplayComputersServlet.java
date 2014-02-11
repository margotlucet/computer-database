package com.excilys.formation.projet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.projet.dao.DAOFactory;
import com.excilys.formation.projet.dto.ComputerDTO;
import com.excilys.formation.projet.service.ComputerService;

/**
 * Servlet implementation class DisplayComputersServlet
 */
@WebServlet("/DisplayComputers")
public class DisplayComputersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayComputersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService computerService = new ComputerService(DAOFactory.INSTANCE_DAO.getComputerDAO());
		List<ComputerDTO> resultList = computerService.getListComputer();
		int quantity = resultList.size();
		request.setAttribute("computerList", resultList);
		request.setAttribute("count",quantity);
		getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
