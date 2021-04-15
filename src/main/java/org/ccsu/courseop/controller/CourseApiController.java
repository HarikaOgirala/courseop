package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseApiController {
	
	@Autowired
	private CoursesService coursesService;
	
	@GetMapping("/api/courseDetails")
	public ResponseEntity<Courses> getCourseDetails(@PathParam(value = "courseNumber") String courseNumber) throws IOException {
		Courses course = coursesService.getCourseDetails(courseNumber);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@GetMapping("/api/courseDetails/all")
	public ResponseEntity<List<Courses>> getCourseDetails() throws IOException {
		List<Courses> course = coursesService.getListOfCourses();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

}
