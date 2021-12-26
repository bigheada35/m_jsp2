package edu.kosmo.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.kosmo.ex.dto.Dto;

public class Dao {

	DataSource datasource;

	public Dao() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {

			}
		}

	}
	
	
	public ArrayList<Dto> list(){
		ArrayList<Dto> dtos = new ArrayList<>();
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
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
				 
				 System.out.println("bid:"+ bid + ", bname:" + bname + ",btitle:" + btitle);
				 
				 Dto dto = new Dto(bid,bname,btitle,bcontent,bdate,bhit,bgroup,bstep,bindent);
				 dtos.add(dto);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}

		}
		
		
		
		return dtos;
	}
	
	
}
