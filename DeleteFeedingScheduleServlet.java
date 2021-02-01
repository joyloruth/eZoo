package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFeedingScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long schedule_id = Long.parseLong(request.getParameter("schedule_id"));
		
		String feeding_time = request.getParameter("feeding_time");
		
		String recurrence = request.getParameter("recurrence");
		
		String food = request.getParameter("food");
		
		String notes = request.getParameter("notes");
		
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule scheduleToDelete = new FeedingSchedule(
				schedule_id, 
				feeding_time, 
				recurrence,
				food,
				notes);
				 
		
		// Call DAO methods for animal and feedingschedules
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		AnimalDAO animalDAO = DAOUtilities.getAnimalDao();
		
		try {
			// remove feeding schedule from all corresponding animals
			List<Animal> animals = animalDAO.getAllAnimals();
			for (Animal animal : animals) {
				if (animal.getFeeding_schedule() == scheduleToDelete.getSchedule_id()) {
					dao.removeFeedingSchedule(animal);
					
					
				}
			
			
			// delete the feeding schedule
			dao.deleteFeedingSchedule(scheduleToDelete);
			
			request.getSession().setAttribute("message",  "Feeding schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
			
		} 
			}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			// change the message
			request.getSession().setAttribute("message",  "Id of " + scheduleToDelete.getSchedule_id() + " not found");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
			request.getSession().setAttribute("message",  "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
		}
	
	

}
}

