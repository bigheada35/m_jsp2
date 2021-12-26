package edu.kosmo.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kosmo.ex.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		System.out.println("bid:"+ bid + "bname:" + bname + "btitle:" + btitle + "bcontent:"+ bcontent);
		
		BDao dao = new BDao();
		dao.modify(bid, bname, btitle, bcontent);

			
	}

}
