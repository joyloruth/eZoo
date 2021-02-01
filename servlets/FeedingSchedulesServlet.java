package com.examples.ezoo.servlets;

import java.io.IOException;
//import java.util.Collections;
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
 * Servlet implementation class FeedingScheduleServlet
 */

@WebServlet("/feedingSchedules")
public class FeedingSchedulesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// retrieve list of Animals from the Database
		FeedingScheduleDAO daos = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> schedules = daos.getAllFeedingSchedules();
		
		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("schedules", schedules);
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
	}


}
