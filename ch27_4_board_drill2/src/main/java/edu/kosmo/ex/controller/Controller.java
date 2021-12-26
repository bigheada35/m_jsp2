package edu.kosmo.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kosmo.ex.command.Command;
import edu.kosmo.ex.command.ListCommand;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response); 
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			String uri = request.getRequestURI();
			String contpath = request.getContextPath();
			String com = uri.substring(contpath.length());
			
			Command command = null;
			String viewPage = null;
			 
			if(com.equals("/list.do")) {
				command = new ListCommand();
				command.execute(request, response);
				
				viewPage ="list.jsp";
			}
			if(com.equals("/content_view.do")) {
				command = new ContentViewCommand();
				command.execute(request, response);
				
				viewPage ="content_view.jsp";
			}
			
			
			
			 RequestDispatcher rd= request.getRequestDispatcher(viewPage);
			 rd.forward(request, response);
			 
			
		
	}
	

}
