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

	<div class="container" id="programInformationData">
		<div class="tabbable tabs-left">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tab2" data-toggle="tab">Programs</a></li>
				<li><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
				<li><a href="/courseop/homefaculty" data-toggle="tab">Faculty</a></li>
			</ul>
		</div>
		<a href="/courseop/" data-toggle="tab">&lt;&lt; Back</a><br> <br>
		<div class="container-fluid" style="border: 2px solid #cecece;">
		
			<h4>
				<p>Details of the Program</p>
			</h4>

			<tr>
				<ul>
					Description :
					<td>${programs.admissionRequirements}</td>
				</ul>
				<ul>
					Number Credits :
					<td>${programs.totalCredits}</td>
				</ul>
		</div>
		<div class="container-fluid" style="border: 2px solid #cecece;">
			<tr>
				<h4>List of mandatory Courses</h4>
				<td><c:forEach items="${programs.requiredCourses}"
						var="prerequisite">
						<li><a href="/courseop/courseDetails?courseNumber=${prerequisite}">${prerequisite}</a></li>
					</c:forEach></td>
			</tr>
		</div>
		<div class="container-fluid" style="border: 2px solid #cecece;">
			<tr>
			<c:set var = "electives" scope = "session" value = "${programs.electives}"/>
			<c:if test = "${not empty electives}" >
				<h4>List of Electives</h4>
				<td><c:forEach items="${programs.electives}"
						var="elective">
						<li><a href="/courseop/courseDetails?courseNumber=${elective}">${elective}</a></li>
					</c:forEach></td>
				</c:if>
			</tr>
		</div>
	</div>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<script type="text/javascript">
document.onreadystatechange = function () {
	  var state = document.readyState
	  if (state == 'interactive') {
	       document.getElementById('contents').style.visibility="hidden";
	  } else if (state == 'complete') {
	      setTimeout(function(){
	         document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	         document.getElementById('"programInformationData"').style.visibility="visible";
	      },1000);
	  }
	}
</script>
</body>
</html>
