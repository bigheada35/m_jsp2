package edu.kosmo.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kosmo.ex.dao.BDao;
import edu.kosmo.ex.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		BDao dao = new BDao();
		
		// temp. test only
		//System.out.println("================temp set 8 ====" + bid);
		//bid = "8";
		
		System.out.println("===bid===" + bid);
		
		BDto dto = dao.contentView(bid);
		request.setAttribute("content_view", dto);
	}

}
