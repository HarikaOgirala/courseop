package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	
	@Autowired
	private CoursesService coursesService;
	
	@RequestMapping("/courses")
	public String getCourses(Map<String, Object> model) throws IOException {
		List<Courses> response = coursesService.getListOfCourses();
		model.put("courses", response);
		return "courses";
	}

	@RequestMapping("/courseTimings")
	public String getCoursesTimings(Map<String, Object> model) {
		//TODO service to get course timings
		return "courseTimings";
	}
	
	@RequestMapping("/courseDetails")
	public String getCourseDetails(Map<String, Object> model, @PathParam("courseNumber") String courseNumber) throws IOException {
		Courses course = coursesService.getCourseDetails(courseNumber);
		model.put("course", course);
		return "courseDetails";
	}
}
