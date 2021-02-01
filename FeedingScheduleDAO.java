package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Main interface used to execute CRUD methods on Feeding Schedule class
 *
 */

public interface FeedingScheduleDAO {
	
	
	/*** Used to retrieve a list of all feeding schedules * @return */
	List<FeedingSchedule> getAllFeedingSchedules();
	
	
	/*** Used to persist the animal to the datastore * @param feedingScheduleToSave */
	void saveFeedingSchedule(FeedingSchedule feedingScheduleToSave) throws Exception;
	
	
	/*** Used to retrive one feedingschedule from the datastore*/
	FeedingSchedule getFeedingSchedule(Animal animal) throws Exception;
	
	
	/*** Used to unassign feedingschedule from an animal*/
	void removeFeedingSchedule(Animal animal) throws Exception;
	
	
	/*** Used to delete feedingschedule*/
	void deleteFeedingSchedule(FeedingSchedule schedule) throws Exception;
	
	
	/*** Used to update feedingschedule*/
	void updateFeedingSchedule(FeedingSchedule schedule) throws Exception;
	
	
	/*** Used to assign feedingschedule from animal*/
	void assignFeedingSchedule(FeedingSchedule schedule, Animal animal) throws Exception;

	
	
	
	

}
