package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;


/**
 * Main interface used to execute CRUD methods on Animal class
 * @author anon
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;
		
	boolean removeFeedingSchedule(Animal schedule) throws Exception;

	boolean assignFeedingSchedule(Animal schedule) throws Exception;

	void updateAnimal(Animal animal) throws Exception;
	
	
	
}
