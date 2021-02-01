package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO {
	
	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() {		
	List<FeedingSchedule> feedingSchedules = new ArrayList<>();
	Connection connection = null;
	Statement stmt = null;

	try {
		connection = DAOUtilities.getConnection();

		stmt = connection.createStatement();

		String sql = "SELECT * FROM feeding_schedule";

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			FeedingSchedule fs = new FeedingSchedule();
			
			fs.setSchedule_id(rs.getLong("schedule_id"));
			fs.setFeeding_time(rs.getString("feeding_time"));
			fs.setRecurrence(rs.getString("recurrence"));
			fs.setFood(rs.getString("food"));
			fs.setNotes(rs.getString("notes"));

			
			feedingSchedules.add(fs);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return feedingSchedules;
}
		

	
	
	@Override
	public void saveFeedingSchedule(FeedingSchedule schedule) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO feeding_schedule VALUES (?,?,?,?,?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from animal into PreparedStatement
			stmt.setLong(1, schedule.getSchedule_id());
			stmt.setString(2, schedule.getFeeding_time());

			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(4, schedule.getFood());
			stmt.setString(5, schedule.getNotes());
			
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Adding a feeding schedule failed: " + schedule);
		}

	}	
	



	public void deleteFeedingSchedule(FeedingSchedule schedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

			try {
					connection = DAOUtilities.getConnection();
					String sql = "DELETE FROM feeding_schedule WHERE schedule_id = (?)";

					// Setup PreparedStatement
					stmt = connection.prepareStatement(sql);

					// Add parameters from feeding schedule into PreparedStatement
					stmt.setLong(1, schedule.getSchedule_id());
				
					success = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
						if (stmt != null)
							stmt.close();
						if (connection != null)
							connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (success == 0) {
				// then update didn't occur, throw an exception
				throw new Exception("Deleting a feeding schedule failed: " + schedule);
			}
		} 
		
		

	
	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) throws Exception {
		
		FeedingSchedule fs = new FeedingSchedule();
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		

		try {
			
				connection = DAOUtilities.getConnection();
				String sql = "SELECT * FROM feeding_schedule WHERE schedule_id = (?)";
				stmt = connection.prepareStatement(sql);

				ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()) 
				{
					fs.setSchedule_id(rs.getInt("schedule_id"));
					fs.setFeeding_time(rs.getString("feeding_time"));
					fs.setRecurrence(rs.getString("recurrence"));
					fs.setFood(rs.getString("food"));
					fs.setNotes(rs.getString("notes"));
				}

			}catch (SQLException e) {
				System.out.println(e);
			}
		
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Retrieving feeding schedule failed: " + animal);
		}
		
	
		return fs;
}




	@Override
	public void removeFeedingSchedule(Animal animal) throws Exception {
				
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		

		try {
			
				connection = DAOUtilities.getConnection();
				String sql = "UPDATE animals SET feeding_schedule = null WHERE animalid = ?";
				stmt = connection.prepareStatement(sql);

				stmt.setLong(1,  animal.getAnimalID());

				success = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Remove feeding schedule from " + animal + "failed");
						}
		
		

	}




	@Override
	public void updateFeedingSchedule(FeedingSchedule schedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;

			try {
					connection = DAOUtilities.getConnection();
					String sql = "UPDATE feeding_schedule SET feeding_time =?, recurrence =?, food =?, notes =? WHERE schedule_id =?";
				

					// Setup PreparedStatement
					stmt = connection.prepareStatement(sql);

					// Add parameters from feeding schedule into PreparedStatement
					
					stmt.setString(1, schedule.getFeeding_time());
					stmt.setString(2, schedule.getRecurrence());
					stmt.setString(3,schedule.getFood());
					stmt.setString(4, schedule.getNotes());
					stmt.setLong(5, schedule.getSchedule_id());
					
					
					success = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
						if (stmt != null)
							stmt.close();
						if (connection != null)
							connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (success == 0) {
				// then update didn't occur, throw an exception
				throw new Exception("Updating feeding schedule failed: " + schedule);
			}// TODO Auto-generated method stub
		
	}




	@Override
	public void assignFeedingSchedule(FeedingSchedule schedule, Animal animal) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE animals SET feeding_schedule = ? WHERE animalid = ?";
			
			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);
			
			// Add parameters from animals into PreparedStatement
			stmt.setLong(1,  schedule.getSchedule_id());
			stmt.setLong(2, animal.getAnimalID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0)
			throw new Exception("Assign feeding schedule failed: " + animal + "and" + schedule);
	} 
	
}
