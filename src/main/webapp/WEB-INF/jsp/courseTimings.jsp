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
  	<li class="active"><a href="#tab1" data-toggle="tab">Courses</a></li>
    <li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
  </ul>
  <div class="tab-content">
  <table class="table">
  <tbody>
   <tr>
      <td align="center"><a href="/courseop/courses">List of Courses</a></td>
    </tr>
    <tr>
      <td align="center"  class="active"><a href="/courseop/courseTimings" data-toggle="tab" class="active">Timings</a></td>
    </tr>
  </tbody>
</table>
  </div>
  </div>
<label class="radio-inline"><input type="radio" onclick="window.location.href='/courseop/courseTimings?semester=Spring';" name="optradio" <c:if test="${semester == 'Spring'}">checked="checked"</c:if>>Spring</label>
<label class="radio-inline"><input type="radio" onclick="window.location.href='/courseop/courseTimings?semester=Summer';" name="optradio" <c:if test="${semester == 'Summer'}">checked="checked"</c:if>>Summer</label>
<label class="radio-inline"><input type="radio" onclick="window.location.href='/courseop/courseTimings?semester=Fall';" name="optradio" <c:if test="${semester == 'Fall'}">checked="checked"</c:if>>Fall</label>
<br>
<br>
<table class="table table-bordered">
  <thead class="thead-light">
    <tr>
     <th scope="col">CourseNumber</th>
     <th scope="col">Type</th>
       <th scope="col">Part of day</th>
      <th scope="col">Timings</th>
      <th scope="col">Day</th>
      <th scope="col">Faculty</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${courses}" var="course">
    <tr>
    <td><a href="/courseop/courseDetails?courseNumber=${course.courseNumber}">${course.courseNumber} - ${course.courseName}</a></td>
    <td>${course.type}</td>
     <td><c:forEach items="${course.partOfDay}" var="pDay">
      	<li>${pDay}</li>
      </c:forEach></td>
      <td><c:forEach items="${course.timings}" var="timing">
      	<li>${timing}</li>
      </c:forEach></td>
     <td><c:forEach items="${course.days}" var="day">
      	<li>${day}</li>
      </c:forEach></td>
      <td>${course.taughtBy.name}</td>
    </tr>
   </c:forEach>
  </tbody>
</table>
</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
