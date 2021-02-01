package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

/**
 * Servlet implementation class AddFeedingScheduleServlet
 */
@WebServlet("/addFeedingSchedule")
public class AddFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		long schedule_id = Long.parseLong(request.getParameter("schedule_id"));
		
		String feeding_time = request.getParameter("feeding_time");

		String recurrence = request.getParameter("recurrence");
		
		String food = request.getParameter("food");
		
		String notes = request.getParameter("notes");
		
		
		
		
		//Create an Feeding schedule object from the parameters
		FeedingSchedule feedingScheduleToSave = new FeedingSchedule(
				schedule_id, 
				feeding_time, 
				recurrence,
				food,
				notes);
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			
			dao.saveFeedingSchedule(feedingScheduleToSave);
			request.getSession().setAttribute("messages", "Feeding Schedule successfully created");
			request.getSession().setAttribute("messageClasses", "alert-success");
			response.sendRedirect("feedingSchedules");


		}catch(SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("messages", "Id of " + feedingScheduleToSave.getSchedule_id() + " is already in use");
			request.getSession().setAttribute("messageClasses", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
			
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("messages", "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClasses", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);

		}
	}

}
