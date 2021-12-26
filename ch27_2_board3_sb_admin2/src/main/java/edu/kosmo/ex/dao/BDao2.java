package edu.kosmo.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.DataSource;

import edu.kosmo.ex.dto.BDto;

public class BDao2 {
	DataSource ds = null;
	public BDao2() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:com/env/jdbc/oracle");
		} catch (Exception e) {

		}
	}
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<>();
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			 con = ds.getConnection();
			 ps = con.prepareStatement("select * from mvc_board order by bgroup desc, bstep asc");
			 rs = ps.executeQuery();
			 
			 while(rs.next()) {
				 
				 int bid = rs.getInt("bid");      	
				 String bname = rs.getString("bname");    
				 String btitle = rs.getString("btitle");   
				 String bcontent = rs.getString("bcontent"); 
				 Timestamp bdate = rs.getTimestamp("bdate"); 
				 int bhit = rs.getInt("bhit");        
				 int bgroup = rs.getInt("bgroup");      
				 int bstep = rs.getInt("bstep");       
				 int bindent = rs.getInt("bindent");  
				 BDto dto = new BDto(bid,bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				 dtos.add(dto);
			 }
		} catch (Exception e) {
				
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
			}
		}
		return dtos;
	}
	
	
	
	
	
}
