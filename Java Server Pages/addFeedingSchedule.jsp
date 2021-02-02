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
	<c:when test="${not empty messages }">
	  <p class="alert ${messageClasses}">${messages }</p>
	<%
	  session.setAttribute("messages", null);
	  session.setAttribute("messageClasses", null);
	%>
	</c:when>
	</c:choose>
		
		<h1>eZoo</h1><h1><small>Create a Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		
		<form action="addFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="schedule_id" class="col-sm-4 control-label">Schedule ID</label>
		    <div class="col-sm-5">
		      <input type="number" class="form-control" id="schedule_id" name="schedule_id" placeholder="Schedule ID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="feeding_time" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-5">
					<select required="required" name="feeding_time" class="form-control">
						
						<option value="01:00 AM">
							01:00 AM
						</option>
						<option value="02:00 AM">
							02:00 AM
						</option>
						<option value="03:00 AM">
							03:00 AM
						</option>
						<option value="04:00 AM">
							04:00 AM
						</option>
						<option value="05:00 AM">
							05:00 AM
						</option>
						<option value="06:00 AM">
							06:00 AM
						</option>
						<option value="07:00 AM">
							07:00 AM
						</option>
						<option value="08:00 AM">
							08:00 AM
						</option>
						<option value="09:00 AM">
							09:00 AM
						</option>
						<option value="10:00 AM">
							10:00 AM
						</option>
						<option value="11:00 AM">
							11:00 AM
						</option>
						
						<option value="12:00 AM">
							12:00 AM
						</option>
						<option value="01:00 PM">
							01:00 PM
						</option>
						<option value="02:00 PM">
							02:00 PM
						</option>
						<option value="03:00 PM">
							03:00 PM
						</option>
						<option value="04:00 PM">
							04:00 PM
						</option>
						<option value="05:00 PM">
							05:00 PM
						</option>
						<option value="06:00 PM">
							06:00 PM
						</option>
						<option value="07:00 PM">
							07:00 PM
						</option>
						<option value="08:00 PM">
							08:00 PM
						</option>
						<option value="09:00 PM">
							09:00 PM
						</option>
						<option value="10:00 PM">
							10:00 PM
						</option>
						<option value="11:00 PM">
							11:00 PM
						</option>
						<option value="12:00 PM">
							12:00 PM
						</option>
						
					</select>
		    </div>
		  </div>
		  	  
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-5">
					<select required="required" name="recurrence" class="form-control">
						<option value="Hourly">
							Hourly
						</option>
						<option value="Daily">
							Daily
						</option>
						<option value="Weekly">
							Weekly
						</option>
						<option value="Biweekly">
							Biweekly
						</option>
						<option value="Monthly">
							Monthly
						</option>
					</select>
				</div>
			</div>	
			
			<div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Diet</label>
		    <div class="col-sm-5">
					<select required="required" name="food" class="form-control">
						
						<option value="Beef">
							beef
						</option>
						<option value="Snake Food">
							frozen rodents
						</option>
						<option value="fruits and vegetables">
							fruits and vegetables
						</option>
						<option value="straw">
							straw
						</option>
						<option value="carcasses">
							carcasses
						</option>
						<option value="Fish Food">
							fish food
						</option>
					</select>
				</div>
			</div>	
		  
		  
		 
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder=" Notes" required="required"/>
		    </div>
		  </div>
		  
		 
		 
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Add Feeding Schedule</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />