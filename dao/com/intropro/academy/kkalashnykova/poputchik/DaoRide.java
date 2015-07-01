package com.intropro.academy.kkalashnykova.poputchik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoRide {
	
	private static Logger log = LogManager.getLogger(RideList.class);
	
	public void getRideById(int id){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Date date = new Date();
		Profile owner = new Profile();
		RideList rides = new RideList();
		Ride result = null;
		try {
			result = rides.createRide("init", "init", date, owner);
		} catch (PoputchikAlreadyExistsException e) {
			// TODO Auto-generated catch block
			log.error("failed to create ride");
		}
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/poputchik","postgres", "postpassw0rd");
			st = con.createStatement();
			rs = st.executeQuery("SELECT start_location FROM ride where ride_id = 1");//("select distinct test_id as tid from test");
			rs.next();
			//result.setStart(rs.getString(1));
			result.setStart(rs.getString("start_location"));
		} catch(SQLException|ClassNotFoundException e ){
			log.error("failed to get connection.");
		}
		finally{
				try {
					if(rs != null){
						rs.close();
						}
					if(st != null){
						st.close();
						}
					if(con != null){
						con.close();
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		
	}
}
