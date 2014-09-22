package fi.omapizzeria.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzaDAO;
import fi.omapizzeria.admin.bean.Pizza;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				PizzaDAO pDao = new PizzaDAO();
				List<Pizza> Pizzat = pDao.haePizzat();
				
				request.setAttribute("Pizzat", Pizzat);
				request.getRequestDispatcher("list.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eka = request.getParameter("nimi");
		String toka = request.getParameter("hinta");
		String kolmas = request.getParameter("id");
		System.out.println(kolmas);
		double tokaDouble = Double.parseDouble(toka);
		
		Pizza p = new Pizza(eka, tokaDouble);
		
		PizzaDAO pDao = new PizzaDAO();
		pDao.lisaaPizza(p);
		pDao.poistaPizza(kolmas);
		List<Pizza> Pizzat = pDao.haePizzat();
		
		request.setAttribute("Pizzat", Pizzat);
		request.getRequestDispatcher("list.jsp").forward(request, response);
		
	
	}
	}


