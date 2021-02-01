package com.examples.ezoo.model;

public class FeedingSchedule {
	
	private long schedule_id = 0L;
	private String feeding_time = "";
	private String recurrence = "";
	private String food = "";
	private String notes = "";
	private String animals = "";
	

	public FeedingSchedule() {};
	
	public FeedingSchedule(long schedule_id,String feeding_time,String recurrence, String food, String notes) {
		
		super();
		this.schedule_id = schedule_id;
		this.feeding_time = feeding_time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
		
		
	}



	
	 /* @return the schedule_id*/
	public long getSchedule_id() {
		return schedule_id;
	}

	/* @param schedule_id the schedule_id to set*/
	public void setSchedule_id(long schedule_id) {
		this.schedule_id = schedule_id;
	}



	/*@return the feeding_time*/
	public String getFeeding_time() {
		return feeding_time;
	}

	/* @param feeding_time the feeding_time to set*/
	public void setFeeding_time(String feeding_time) {
		this.feeding_time = feeding_time;
	}



	/* @return the recurrence*/
	public String getRecurrence() {
		return recurrence;
	}

	/* @param recurrence the recurrence to set */
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}



	/* @return the food */
	public String getFood() {
		return food;
	}
	/* @param food the food to set */
	public void setFood(String food) {
		this.food = food;
	}



	/* @return the notes */
	public String getNotes() {
		return notes;
	}

	/* @param notes the notes to set */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	public String getAnimals() {
		return animals;
	}

	public void setAnimals(String animals) {
		this.animals = animals;
	}
	
	
	@Override
	public String toString() {
		return "FeedingSchedule [schedule_id=" + schedule_id + ", feeding_time=" + feeding_time + ", recurrence=" + recurrence + ", food="
				+ food + ", notes=" + notes + "]";
	}
	
	
}
