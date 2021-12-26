package edu.kosmo.ex.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kosmo.ex.dto.BDto;

import edu.kosmo.ex.dao.BDao;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub


		String bid = request.getParameter("bid");
		BDao dao = new BDao();
		BDto dto = dao.reply_view(bid);
		
		request.setAttribute("reply_view", dto);	
		
		
	}

}
