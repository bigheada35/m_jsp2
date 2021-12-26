package edu.kosmo.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kosmo.ex.command.BCommand;
import edu.kosmo.ex.command.BContentCommand;
import edu.kosmo.ex.command.BDeleteCommand;
import edu.kosmo.ex.command.BListCommand;
import edu.kosmo.ex.command.BModifyCommand;
import edu.kosmo.ex.command.BReplyCommand;
import edu.kosmo.ex.command.BReplyViewCommand;
import edu.kosmo.ex.command.BWriteCommand;

/**
 * Servlet implementation class BController
 */


//@WebServlet("/BController")
// 전자 정부 프레임 이 이렇게 쓴다고 함.
@WebServlet("*.do")
public class BController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//지우고,, response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet");
		actionDo(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//지우고, doGet(request, response);
		
		System.out.println("doGet");
		actionDo(request,response);

	}

	//이것을 추가함.  반드시  throws ServletException, IOException  것도 넣으시요..
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("actionDo");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		if(com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request,response);
			viewPage = "list.jsp";
		}

		System.out.println("---    " + com);

		//--- 핵심 코드 2 
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}
	
	
}
