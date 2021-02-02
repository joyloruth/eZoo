<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">

	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
 
		<h1>eZoo</h1><h1><small> Assign a Feeding Schedule </small></h1>
		<hr class="paw-primary">
				
			<table class="table table-striped table-hover table-responsive ezoo-datatable">
				<thead>
					<tr>
						<th class="text-center">Assign</th>
						<th class="text-center">Schedule ID</th>
						<th class="text-center">Feeding Time</th>
						<th class="text-center">Recurrence</th>
						<th class="text-center">Food</th>
						<th class="text-center">Notes</th>
						<th class="text-center">Animals Assigned</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="schedule" items="${feedingSchedules}">
						<tr>
							<td>
								<form action="assignFeedingSchedule" method="post">
									<input type="hidden" value="${schedule.schedule_id}" name="schedule_id" />
									<input type="hidden" value="${schedule.feeding_time}" name="feeding_time" />
									<input type="hidden" value="${schedule.recurrence}" name="recurrence" />
									<input type="hidden" value="${schedule.food}" name="food" />
									<input type="hidden" value="${schedule.notes}" name="notes" />
									<input type="hidden" value="${animalID}" name="animalID" />
									<button type="submit" class="btn btn-primary">Assign</button>
								</form>
							</td>
							<td><fmt:formatNumber value="${schedule.schedule_id}"/></td>
							<td><c:out value="${schedule.feeding_time}" /></td>
							<td><c:out value="${schedule.recurrence}" /></td>
							<td><c:out value="${schedule.food}" /></td>
							<td><c:out value="${schedule.notes}" /></td>
							<td><c:out value="${schedule.animals}" /></td>									
						</tr>
					</c:forEach>
				</tbody>
			</table>
	
	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />