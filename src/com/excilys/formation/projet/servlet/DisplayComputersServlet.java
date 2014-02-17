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
import com.excilys.formation.projet.mapper.ComputerDTOMapper;
import com.excilys.formation.projet.service.ComputerService;
import com.excilys.formation.projet.service.impl.ComputerServiceImpl;

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
		ComputerService computerService = new ComputerServiceImpl(DAOFactory.INSTANCE_DAO.getComputerDAO());
		
		List<ComputerDTO> resultList = null;
		String page = request.getParameter("page");
		String resultsPerPage = request.getParameter("resultsPerPage");
		String search = request.getParameter("search");	
		
		int quantity = 0; 
		int nbResultsPerPage = 10;
		int pageNum = 1;
		int currPage = 1;
		
		
		if((page!=null)&&(!page.equals(""))){
			pageNum = Integer.parseInt(page);
			currPage = pageNum;
			System.out.println(pageNum);
		}
		if((resultsPerPage!=null)&&(!resultsPerPage.equals(""))){
			nbResultsPerPage = Integer.parseInt(resultsPerPage);
		}
		int nbPages = quantity/nbResultsPerPage;
		
		if((search!=null)&&(!search.equals(""))){
			quantity = computerService.getNumber(search);
			resultList = ComputerDTOMapper.toComputerDTOList(computerService.getPage(pageNum, nbResultsPerPage, search));
		}
		else{
			quantity = computerService.getNumber();
			resultList = ComputerDTOMapper.toComputerDTOList(computerService.getPage(pageNum, nbResultsPerPage));
		}

		
		request.setAttribute("computerList", resultList);
		request.setAttribute("count",quantity);
		request.setAttribute("pages", nbPages);
		request.setAttribute("currPage", currPage);
		request.setAttribute("search", search);
		getServletContext().getRequestDispatcher("/WEB-INF/view/dashboard.jsp").forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
