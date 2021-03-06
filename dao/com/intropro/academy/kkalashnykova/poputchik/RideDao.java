package com.intropro.academy.kkalashnykova.poputchik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RideDao {
	
	private ResultSet resultSet= null;	
	private static Logger log = LogManager.getLogger(RideList.class);
	
	public String getRideById(int id){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		

		
		String start_location = null;
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
			PostgresDao.setClassForName();
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/poputchik","postgres", "postpassw0rd");
			st = con.createStatement();
			rs = st.executeQuery("SELECT start_location FROM poputchik_postgres.ride where ride_id = " + id);
			rs.next();
			result.setStart(rs.getString("start_location"));
			start_location = rs.getString("start_location");
		} catch(SQLException|ClassNotFoundException e ){
			log.error("Failed to get Ride by ID.", e);
			throw new PoputchikDaoFailedToRead("Failed to get Ride by ID = " + id, e);
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
		
		return start_location;
		
	}
	
	public void createRide(Ride ride){
		log.info("Insert Ride into database started.");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		ResourceBundle rb = ResourceBundle.getBundle("connection");
		PreparedStatement insertRide = null;
		String insertString = "INSERT INTO " + rb.getString("schema") + ".ride(start_location, finish_location, start_datetime, status) VALUES (?, ?, ?, ?)";
		
		try{
			PostgresDao.setClassForName();
			con = DriverManager.getConnection(rb.getString("connectionString"), rb.getString("username"), rb.getString("password"));
			insertRide = con.prepareStatement(insertString);
			insertRide.setString(1, ride.getStart());
			insertRide.setString(2, ride.getFinish());
			insertRide.setDate(3, new java.sql.Date(ride.getDateTime().getTime()));
			insertRide.setString(4, RideStatus.active.toString());
			insertRide.executeUpdate();
			log.info("Executed query: " + insertRide);
		} catch(SQLException|ClassNotFoundException e ){
			log.error("Failed to insert new ride via " + insertRide, e);
			throw new PoputchikDaoFailedToRead("Failed to insert new ride into DB:", e);
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
		log.info("Successfully inserted Ride into database.");
	}
	
	public static void deleteRide(int id){
		log.info("Delete Ride from database started.");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		ResourceBundle rb = ResourceBundle.getBundle("connection");
		PreparedStatement deleteRide = null;
		String deleteString = "DELETE FROM" + rb.getString("schema") + ".ride WHERE ride_id = ?";
		
		try{
			PostgresDao.setClassForName();
			con = DriverManager.getConnection(rb.getString("connectionString"), rb.getString("username"), rb.getString("password")); 
			deleteRide = con.prepareStatement(deleteString);
			deleteRide.setInt(1, id);
			deleteRide.executeUpdate();
			log.info("Executed query: " + deleteRide);
		} catch(SQLException|ClassNotFoundException e ){
			log.error("Failed to delete a ride via " + deleteRide, e);
			throw new PoputchikDaoFailedToDelete("Failed to delete ride from DB:", e);
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
		log.info("Successfully deleted Ride from database.");
	}
}
