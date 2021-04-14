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

	<div class="container" id="homeFacultyData">

		<div class="tabbable tabs-left">
  <ul class="nav nav-tabs">
   <li><a href="/courseop/" data-toggle="tab">Programs</a></li>
  	<li><a href="/courseop/homecourses" data-toggle="tab">Courses</a></li>
     <li class="active"><a href="#tab2" data-toggle="tab">Faculty</a></li>
  </ul>
  <div class="tab-content">
  <table class="table">
  <tbody>
    <tr>
      <td align="center"><a href="/courseop/permanentFaculty" data-toggle="tab">Permanent Faculty</a></td>
    </tr>
    <tr>
      <td align="center"><a href="/courseop/adjunctFaculty" data-toggle="tab">Adjunct Faculty</a></td>
    </tr>
  </tbody>
</table>
  </div>
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
	         document.getElementById('"homeFacultyData"').style.visibility="visible";
	      },1000);
	  }
	}
</script>
</body>

</html>
