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
		<a href="/courseop/" data-toggle="tab">&lt;&lt; Back</a><br> <br>
		<table class="table table-bordered">
			<thead class="thead-light">
				<tr>
					<th scope="col">Course Number</th>
					<th scope="col">Course Name</th>
					<th scope="col">Course Pre-requisites</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courses}" var="course">
					<tr>
						<td><a href="/courseop/courseDetails?courseNumber=${course.courseNumber}">${course.courseNumber}</a></td>
						<td>${course.courseName}</td>
						<td><c:forEach items="${course.prerequisite}"
								var="prerequisite">
								<c:if test="${prerequisite == 'null' || prerequisite == 'None' }">
									<li>None</li>
								</c:if>
								<c:if test="${prerequisite != 'null'}">
									<li><a href="/courseop/courseDetails?courseNumber=${prerequisite}" >${prerequisite}</a></li>
								</c:if>

							</c:forEach></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
