package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.model.Animal;

/**
 * Servlet implementation class RemoveFS
 */
@WebServlet("/removeFeedingSchedule")

public class RemoveFeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Animals from the Database
		AnimalDAO dao = DAOUtilities.getAnimalDao();

		List<Animal> animals = dao.getAllAnimals();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("animals", animals);

		request.getRequestDispatcher("removeFeedingSchedule.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long animalID = Long.parseLong(request.getParameter("animalID"));

		Animal scheduleToRemove = new Animal();

		// get parameters and call DAO
		scheduleToRemove.setAnimalID(animalID);
		
		AnimalDAO dao = DAOUtilities.getAnimalDao();

		boolean success = false;
		
		try {
			success = dao.removeFeedingSchedule(scheduleToRemove);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		if (success) {
			request.getSession().setAttribute("message", "Animal feeding schedule successfully removed");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCare");
		} else {
			request.getSession().setAttribute("message","There was a problem removing the feeding schedule");
			request.getSession().setAttribute("messageClass", "alert-danger");

			request.getRequestDispatcher("removeFeedingSchedule").forward(request, response);
		}

	}
}