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
   	<li><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
  	<li class="active"><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
  </ul>
  <div class="tab-content">
  <table class="table">
  <tbody>
    <tr>
      <td align="center" class="active"><a href="/courseop/permanentFaculty" data-toggle="tab" class="active">Permanent Faculty</a></td>
    </tr>
   <tr>
      <td align="center"><a href="/courseop/adjunctFaculty" data-toggle="tab" class="active">Adjunct Faculty</a></td>
    </tr>
  </tbody>
</table>
  </div>
  </div>
<table class="table table-bordered">
  <thead class="thead-light">
    <tr>
      <th scope="col">firstName</th>
      <th scope="col">lastName</th>
      <th scope="col">email</th>
      <th scope="col">website</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${faculty}" var="faculty">
    <tr>
      <td>${faculty.firstName}</td>
      <td>${faculty.lastName}</td>
      <td>${faculty.email}</td>
      <td>
	      <c:if test="${faculty.website != 'null'}">
	      		<a href="${faculty.website}" target="_blank">${faculty.website}</a>
	      </c:if>
      </td>
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

</html>
