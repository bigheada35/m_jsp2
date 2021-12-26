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

public class BDao {
	// 주의  javax.sql  안에 있는 것인,  커넥션 풀  데이터 객체
	private DataSource dataSource;
	
	public BDao() {
		try {
			//주의 javax.  안에 있는 것
			Context context= new InitialContext();
			
			// 주의 Servers --> Tomcat v9.0  --> context.xml 안에 정의된
			/*
		 	<Resource 
				auth="Container" 
				driverClassName="oracle.jdbc.OracleDriver" 
				maxIdle="10" 
				maxTotal="20" 
				maxWaitMillis="-1" 
				name="jdbc/oracle"
				password="tiger" 
				type="javax.sql.DataSource" 
				url="jdbc:oracle:thin:@127.0.0.1:1521:xe" 
				username="scott"
			/>	
			*/
			// 중에서 name="jdbc/oracle"  부분의 이름이 들어가도록 
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
	public ArrayList<BDto> list(){
			ArrayList<BDto> dtos = new ArrayList<>();
			
			
			Connection con = null;
			PreparedStatement preSta = null;
			ResultSet rs = null;
			
			try {
				String query = "select * from mvc_board order by bgroup desc, bstep asc";
				
				// 예전 방법 , 커넥션 풀 안쓸때
				//Calss.forName(driver);
				
				con = dataSource.getConnection();
				preSta = con.prepareStatement(query);
				// 내부적으로 commit 이 auto로 진행이 된다.
				rs = preSta.executeQuery();
				
				while(rs.next()) {
					
					int bid			= rs.getInt("bid");
					String bname	= rs.getString("bname");
					String btitle	= rs.getString("btitle");
					String bcontent	= rs.getString("bcontent");
					Timestamp bdate	= rs.getTimestamp("bdate");
					int bhit		= rs.getInt("bhit");
					int bgroup		= rs.getInt("bgroup");
					int bstep		= rs.getInt("bstep");
					int bindent		= rs.getInt("bindent");
					
					BDto dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup,bstep, bindent);
					dtos.add(dto);
					
					
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
					if(rs != null) rs.close();
					if(preSta != null) preSta.close();
					if(con!= null ) con.close();
					
				} catch (Exception e2) {
						e2.printStackTrace();
				}
			}
			
			
			
			return dtos;
	}


	public void write(String bname, String btitle, String bcontent) {
		// TODO Auto-generated method stub
	
		System.out.println("--- write");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			//주의  sql 구문 " " 안에 ; 안들어 가도록 주의 하기.
			String query = "insert into mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			
			System.out.println("bname:" +bname);
			System.out.println("btitle:" + btitle);
			System.out.println("bcontent:" + bcontent);
			
			preparedStatement = connection.prepareStatement(query);
			
			// 물음표 부분에 차례대로 셋팅 하는 것임..
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			// 내부적으로 commit 이 auto로 진행이 된다.
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)	preparedStatement.close();
				if(connection != null) 	connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	
	public BDto contentView(String bid) {
		// TODO Auto-generated method stub
		
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement preSta = null;
		ResultSet rs = null;
		
		try {
			
// 어떻게 파람을 안으로 집어 넣을수 수 있을까???
//String query = "select bid, bhit,bcontent from mvc_board where bid=" + bid;
			System.out.println("-----  " + bid);
String query = "select * from mvc_board where bid=" + bid;	
			System.out.println("-----  " + query);
//String query = "select * from mvc_board order by bgroup desc, bstep asc";

			// 예전 방법 , 커넥션 풀 안쓸때
			//Calss.forName(driver);
			
			System.out.println("--a---  ");
			con = dataSource.getConnection();
			preSta = con.prepareStatement(query);
			// 내부적으로 commit 이 auto로 진행이 된다.
			rs = preSta.executeQuery();
			
			
			//주의 :  1개의 열 row만 얻를 것이라서, while 에 rs.next()를 사용하지 않으면 시스템이 멈춘다. 주의~!!!
			while(rs.next()) {
				System.out.println("--b---  ");
				
				int bid2		= rs.getInt("bid");
				String bname	= rs.getString("bname");
				String btitle	= rs.getString("btitle");
				String bcontent	= rs.getString("bcontent");
				Timestamp bdate	= rs.getTimestamp("bdate");
				int bhit		= rs.getInt("bhit");
				int bgroup		= rs.getInt("bgroup");
				int bstep		= rs.getInt("bstep");
				int bindent		= rs.getInt("bindent");
			
				System.out.println("--c---  ");
				dto = new BDto(bid2, bname, btitle, bcontent, bdate, bhit, bgroup,bstep, bindent);
				System.out.println(bid2 + ", " +
						bname + ", " +
						btitle + ", " +
						bcontent + ", " +
						bdate + ", " +
						bhit + ", " +
						bgroup + ", " +
						bstep + ", " +
						bindent + ", "
						);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}finally {
			try {
				if(con!= null ) con.close();
				if(preSta != null) preSta.close();
				if(rs != null) rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		
		return dto;
	}
	
	
	public void modify(String bid2, String bname2, String btitle2, String bcontent2) {
		// TODO Auto-generated method stub
	
		System.out.println("--- modify");
		
		Connection con = null;
		PreparedStatement preSta = null;
		ResultSet rs = null;
		
		//int bid = 0;
		String bname = null;
		String btitle = null;
		String bcontent	 = null;
		//Timestamp bdate = null;
		//int bhit = 0;
		//int bgroup = 0;
		//int bstep = 0;
		//int bindent = 0;
		
		
		try {
			con = dataSource.getConnection();

			// write 는  신규 열을 insert , 		

			// 
			//  읽기 (select)
			//
			
			//주의  sql 구문 " " 안에 ; 안들어 가도록 주의 하기.
			
			// modify는 id를 찾아서,  update ,
			String query = "select * from mvc_board where bid=" + bid2;	
			
			System.out.println("---modkfy---select:" + query);
			
			System.out.println("bname2:" +bname2);
			System.out.println("btitle2:" + btitle2);
			System.out.println("bcontent2:" + bcontent2);
			
			con = dataSource.getConnection();
			preSta = con.prepareStatement(query);
			// 내부적으로 commit 이 auto로 진행이 된다.
			rs = preSta.executeQuery();
		
			while(rs.next()) {
				
				//bid	= rs.getInt("bid");
				bname	= rs.getString("bname");
				btitle	= rs.getString("btitle");
				bcontent	= rs.getString("bcontent");
				//bdate		= rs.getTimestamp("bdate");
				//bhit		= rs.getInt("bhit");
				//bgroup	= rs.getInt("bgroup");
				//bstep		= rs.getInt("bstep");
				//bindent	= rs.getInt("bindent");

			}
			//
			// 쓰기 (update)
			//
			
			//
			// test - 1 
			//
			
			//String query2 = "insert into mvc_board (bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			String query2 = "update mvc_board set bname=?,btitle=?,bcontent=? where bid=" + bid2;
			System.out.println("---modify---update:" + query2);
			preSta = con.prepareStatement(query2);
			// 물음표 부분에 차례대로 셋팅 하는 것임..
			preSta.setString(1, bname2);
			preSta.setString(2, btitle2);
			preSta.setString(3, bcontent2);
			
			// 내부적으로 commit 이 auto로 진행이 된다.
			int rn = preSta.executeUpdate();
			
			//
			//test - 2
			//
			
			//String query2 = "update mvc_board set bname=" + bname2 + ",btitle=" + btitle2 + ",bcontent=" + bcontent2 +"where bid=" + bid2;
			//System.out.println("---modify---update:" + query2);
			//con.prepareStatement(query2);
			//preSta.executeUpdate();
					
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)	rs.close();
				if(preSta != null)	preSta.close();
				if(con != null) 	con.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	private void replyShape(String bgroup, String bstep) {
		
		System.out.println("--- replyShape");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			//
			// step의 값을 +1 모두 증가를 시킨다.
			// 현재의 
String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
					
			
			System.out.println("bgroup:" +bgroup);
			System.out.println("bstep:" + bstep);
			
			preparedStatement = connection.prepareStatement(query);
			
			// 물음표 부분에 차례대로 셋팅 하는 것임..
			preparedStatement.setString(1, bgroup);
			preparedStatement.setString(2, bstep);

			// 내부적으로 commit 이 auto로 진행이 된다.
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)	preparedStatement.close();
				if(connection != null) 	connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		
	}
	
	
	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep,
			String bindent) {
		// TODO Auto-generated method stub
		
		//====================================
		System.out.println("---- replyShape");
		replyShape(bgroup, bstep);
		
		//-------------------------------
		System.out.println("---- reply");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			
			
String query = "insert into mvc_board (bid, bname, btitle, bcontent, bgroup, bstep, bindent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
					
			
			System.out.println("bgroup:" +bgroup);
			System.out.println("bstep:" + bstep);
			
			preparedStatement = connection.prepareStatement(query);
			
			// 물음표 부분에 차례대로 셋팅 하는 것임..
			preparedStatement.setString(1, bname);
			preparedStatement.setString(2, btitle);
			preparedStatement.setString(3, bcontent);
			preparedStatement.setInt(4, Integer.parseInt(bgroup));
			preparedStatement.setInt(5, Integer.parseInt(bstep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bindent) + 1);
			

			// 내부적으로 commit 이 auto로 진행이 된다.
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)	preparedStatement.close();
				if(connection != null) 	connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
		
	}


	public BDto reply_view(String bid2) {
		// TODO Auto-generated method stub
		BDto dto = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(bid2));
			// 내부적으로 commit 이 auto로 진행이 된다.
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				int bid = resultSet.getInt("bid");
				String bname = resultSet.getString("bname");
				String btitle = resultSet.getString("btitle");
				String bcontent = resultSet.getString("bcontent");
				Timestamp bdate = resultSet.getTimestamp("bdate");
				int bhit = resultSet.getInt("bhit");
				int bgroup = resultSet.getInt("bgroup");
				int bstep = resultSet.getInt("bstep");
				int bindent = resultSet.getInt("bindent");
				
				dto = new BDto(bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return dto;
	}


	public BDto delete(String bid) {
		// TODO Auto-generated method stub
		
		System.out.println("--- delete");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			String query = "delete from mvc_board where bid=?";
					
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			int rn = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)	preparedStatement.close();
				if(connection != null) 	connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return null;
	}



	
	
	
}
