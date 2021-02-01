package com.examples.ezoo.servlets;

import java.io.IOException;
//import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class UpdateFeedingScheduleServlet
 * 		
 * 		
 */
@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;		
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		
		List<FeedingSchedule> feedingSchedules = dao.getAllFeedingSchedules();
		
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		
		request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);}
		

	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		long schedule_id = Long.parseLong(request.getParameter("schedule_id"));
				
		String feeding_time = request.getParameter("feeding_time");
		
		String recurrence = request.getParameter("recurrence");
		
		String food = request.getParameter("food");
		
		String notes = request.getParameter("notes");
		
		
		
		// Create a FeedingSchedule object from the parameters
		FeedingSchedule scheduleToUpdate = new FeedingSchedule(
				schedule_id,
				feeding_time,
				recurrence,
				food,
				notes);
		
		// Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.updateFeedingSchedule(scheduleToUpdate);
			request.getSession().setAttribute("message",  "Feeding schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("message",  "There was a problem updating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("updateFeedingSchedules.jsp").forward(request, response);
		}
		
	}
	
}