<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<style>
#load{
    width:100%;
    height:100%;
    position:fixed;
    z-index:9999;
    background:url("https://i.gifer.com/YCZH.gif") no-repeat center center rgba(0,0,0,0.25)
}
</style>
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
<div id="load"></div>

	<div class="container" id="coursesData">

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
      <td align="center" class="active"><a href="/courseop/courses" data-toggle="tab" class="active">List of Courses</a></td>
    </tr>
   <tr>
      <td align="center"><a href="/courseop/courseTimings" data-toggle="tab">Timings</a></td>
    </tr>
  </tbody>
</table>
  </div>
  </div>
<table class="table table-bordered">
  <thead class="thead-light">
    <tr>
      <th scope="col">CourseNumber</th>
      <th scope="col">CourseName</th>
      <th scope="col">Pre-requisite</th>
      <th scope="col">CourseType</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${courses}" var="course">
    <tr>
      <td><a href="/courseop/courseDetails?courseNumber=${course.courseNumber}">${course.courseNumber}</a></td>
      <td>${course.courseName}</td>
      <td>
	      <c:forEach items="${course.prerequisite}" var="prerequisite">
	      <c:if test="${prerequisite == 'null' || prerequisite == 'None'}">
   			<li>None</li>
	  	</c:if>
	  	<c:if test="${prerequisite != 'null' && prerequisite != 'None'}">
    		<li><a href="/courseop/courseDetails?courseNumber=${prerequisite}">${prerequisite}</a></li>
	  	</c:if>
	      	
	      </c:forEach>
      </td>
	  
      <td>${course.type}</td>
    </tr>
   </c:forEach>
  </tbody>
</table>
		</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

<script type="text/javascript">
document.onreadystatechange = function () {
	  var state = document.readyState
	  if (state == 'interactive') {
	       document.getElementById('contents').style.visibility="hidden";
	  } else if (state == 'complete') {
	      setTimeout(function(){
	         document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	         document.getElementById('"coursesData"').style.visibility="visible";
	      },1000);
	  }
	}
</script>

</html>
