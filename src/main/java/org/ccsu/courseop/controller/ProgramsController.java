package org.ccsu.courseop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.model.Programs;
import org.ccsu.courseop.service.CoursesService;
import org.ccsu.courseop.service.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProgramsController {
	
	@Autowired
	private CoursesService coursesService;
	@Autowired
	private ProgramsService programService;
	
	
	@RequestMapping("/programs")
	public String getPrograms(Map<String, Object> model) {
		//TODO service to get course timings
		return "programs";
	}
	
	@RequestMapping("/programGradCourses")
	public String getProgramGradCourses(Map<String, Object> model) throws IOException {
		List<Courses> response = coursesService.getListOfGraduateCourses();
		model.put("courses", response);
		return "programCourses";
	}
	
	@RequestMapping("/programUnderGradCourses")
	public String getProgramUnderGradCourses(Map<String, Object> model) throws IOException {
		List<Courses> response = coursesService.getListOfUnderGraduateCourses();
		model.put("courses", response);
		return "programCourses";
	}
	
	@RequestMapping("/programInformationCS")
	public String getProgramInformationCS(Map<String, Object> model) throws IOException {
		Programs response = programService.getProgramInformation("CIT_CS");
		model.put("programs", response);
		return "programInformation";
	}
	
	@RequestMapping("/programInformationSE")
	public String getProgramInformationCIT(Map<String, Object> model) throws IOException {
		Programs response = programService.getProgramInformation("CIT_SE");
		model.put("programs", response);
		return "programInformation";
	}
}

