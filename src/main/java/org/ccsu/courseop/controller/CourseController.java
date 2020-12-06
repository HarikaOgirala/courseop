package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
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
	public String getSchedule(Map<String, Object> model, @PathParam("semester") String semester) throws IOException {
		List<Courses> response = coursesService.getSchedule(semester);
		model.put("courses", response);
		if(null == semester) {
			semester = "Spring";
		}
		model.put("semester",semester);
		return "courseTimings";
	}
	
	@RequestMapping("/courseDetails")
	public String getCourseDetails(Map<String, Object> model, @PathParam("courseNumber") String courseNumber) throws IOException {
		Courses course = coursesService.getCourseDetails(courseNumber);
		model.put("course", course);
		return "courseDetails";
	}
}
