<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">CourseOp</a>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="tabbable tabs-left">
		  <ul class="nav nav-tabs">
		   	<li><a href="/courseop/" data-toggle="tab">Programs</a></li>
		  	<li class="active"><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
		    <li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
		  </ul>
		  <div class="tab-content">
			  <table class="table">
			  <tbody>
			    <tr>
			      <td align="center" class="active">Details of the Program</td>
			    </tr>
			  </tbody>
			</table>
		</div>
  		</div>
			<table class="table table-bordered"> 
					<tbody>
						<tr>
							<td>Course Number</td>
							 <td>${course.courseNumber}</td>
						</tr>
						<tr>
							<td>Course Name</td>
							<td>${course.courseName}</td>
						</tr>
						<tr>
							<td>Course Type</td>
							<td>${course.type}</td>
						</tr>
						<tr>
							<td>Faculty</td>
							<td>${course.taughtBy.name}</td>
						</tr>
						<tr>
							<td>Course CRN</td>
							<td>${course.courseCRN}</td>
						</tr>
						<tr>
							<td>Course Description</td>
							<td>${course.courseDesc}</td>
						</tr>
						<tr>
							<td>Semesters Available</td>
							<td>
							<c:forEach items="${course.semesters}" var="sem">
									<li>${sem}</li>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>Days Available</td>
							<td><c:forEach items="${course.days}" var="day">
									<li>${day}</li>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>Timings Available</td>
							<td><c:forEach items="${course.timings}" var="time">
									<li>${time}</li>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>Course Prerequisites</td>
							<td><c:forEach items="${course.prerequisite}"
									var="prerequisite">
									<c:if test="${prerequisite != 'null'}">
										<li><a href="/courseop/courseDetails?courseNumber=${prerequisite}">${prerequisite}</a></li>
									</c:if>
									<c:if test="${prerequisite == 'null'}">
										<li>None</li>
									</c:if>
								</c:forEach>
							</td>
						</tr>
					</tbody>
				</table>
				
</div>
<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>