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
	   <li class="active"><a href="#tab2" data-toggle="tab">Programs</a></li>
	  	<li><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
	     <li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
	  </ul>
	</div>
	<div class="container-fluid" style="border:2px solid #cecece;">
   	 	<h4 class="card-title">
       		 <span class="float-right">Graduate Programs</span>
		</h4>
		<ul>
			<a href="/courseop/programInformationCS" data-toggle="tab">Program Information CS</a> 
		</ul>
		<ul>
			<a href="/courseop/programInformationSE" data-toggle="tab">Program Information SE</a>
		</ul> 
	</div>   
    <div class="container-fluid" style="border:2px solid #cecece;">
	   <h4 class="card-title">
       		 <span class="float-right">Under Graduate Programs</span>
		</h4>    
		<ul>
			<a href="/courseop/programInformationCSAlternative" data-toggle="tab">Computer Science Alternative</a>
		</ul> 
		<ul>
			<a href="/courseop/programInformationCSHonors" data-toggle="tab">Computer Science Honors BS</a>
		</ul> 
		<ul>
			<a href="/courseop/programInformationMinor" data-toggle="tab">Computer Science Minor</a>
		</ul> 
		<ul>
			<a href="/courseop/programInformationCyberSecurity" data-toggle="tab">Cyber Security Concentration</a>
		</ul>                                            
	</div>
	<div class="container-fluid" style="border:2px solid #cecece;">
	   <h4 class="card-title">
       		 <span class="float-right">All Courses Available</span>
		</h4>    
		<ul>
			<a href="/courseop/programGradCourses" data-toggle="tab">Graduate courses</a>
		</ul> 
		<ul> 
			<a href="/courseop/programUnderGradCourses" data-toggle="tab">Undergraduate courses</a>
		</ul>                                            
	</div>
	 
</div>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
