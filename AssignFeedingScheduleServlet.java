package com.examples.ezoo.servlets;

import java.io.IOException;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class assignFeedingScheduleServlet
 */
@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		long animalID = Integer.parseInt(request.getParameter("animalID"));
		
		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();

		//call animal DAO
		AnimalDAO animalDAO = DAOUtilities.getAnimalDao();
		List<Animal> animals = animalDAO.getAllAnimals();
		
		
		for (FeedingSchedule schedule : feedingSchedules) {
			String animalSchedule = "";
			int count = 0;
			for (Animal animal : animals) {
				if (schedule.getSchedule_id() == animal.getFeeding_schedule()) {
					count++;
					String string = "";
					if (count > 1) {
						string = ", ";
					}
					animalSchedule += string + animal.getName() + 
							"[" + animal.getAnimalID() + "]";	
				}
			}
			schedule.setAnimals(animalSchedule); 
		}
		
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		request.getSession().setAttribute("animalID", animalID);
		
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get Parameters & convert to long since parameters are always Strings
		long animalID = Long.parseLong(request.getParameter("animalID"));
		
		// Call DAO methods
		AnimalDAO adao= DAOUtilities.getAnimalDao();
		FeedingScheduleDAO fsdao = DAOUtilities.getFeedingScheduleDao();

		try {
			
			List<Animal> animals = adao.getAllAnimals();
			
			Animal animal = new Animal();
			for (Animal a : animals) {
				if (a.getAnimalID() == animalID)
					animal = a;
			}
			
			// remove feeding schedule assignment from an animal
			if (animal.getFeeding_schedule() > 0) {
				fsdao.removeFeedingSchedule(animal);
				request.getSession().setAttribute("message",  "Feeding schedule successfully removed.");
			}
			// assign a feeding schedule to an animal
			else {
				int id = Integer.parseInt(request.getParameter("schedule_id")); 
				String time = request.getParameter("feeding_time");
				String recurrence = request.getParameter("recurrence");
				String food = request.getParameter("food");
				String notes = request.getParameter("notes");
				FeedingSchedule fs = new FeedingSchedule (
					id,
					time,
					recurrence,
					food,
					notes
				);
				
				fsdao.assignFeedingSchedule(fs, animal);
				
				request.getSession().setAttribute("message",  "Feeding schedule successfully assigned");
			}
			
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
			request.getSession().setAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);
		}
		
	}
}