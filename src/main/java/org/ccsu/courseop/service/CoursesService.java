package org.ccsu.courseop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.ccsu.courseop.model.Courses;
import org.ccsu.courseop.model.Faculty;
import org.ccsu.courseop.model.Programs;
import org.ccsu.courseop.util.AdvisorSchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class CoursesService {

	@Autowired
	private AdvisorSchemaFactory schemaFactory;
	QueryExecution qexec;
	Query query;

	public List<Courses> getListOfCourses() throws IOException {
		List<Courses> response = new ArrayList<Courses>();
		response.addAll(getListOfUnderGraduateCourses());
		response.addAll(getListOfGraduateCourses());
		return response;
	}

	/**
	 * SELECT QUERY 1: Graduate courses
	 * 
	 * @throws IOException
	 */
	public List<Courses> getListOfGraduateCourses() throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> " +

				"JSON { \"course\": ?course ,\r\n" + "\"courseName\" : ?course_name , \r\n"
				+ "\"courseNumber\" : ?course_number ,\r\n" + "\"coursePrerequisite\" : ?prerequisite }" + "where {\r\n"
				+ "?course rdf:type AdvisoryBot:Graduate .\r\n"
				+ "OPTIONAL { ?course AdvisoryBot:hasPrerequisite ?prerequisite .}\r\n"
				+ "?course AdvisoryBot:courseName ?course_name .\r\n"
				+ "?course AdvisoryBot:courseNumber ?course_number .}";

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		List<Courses> response = new ArrayList<Courses>();
		HashSet<String> hsetPrerequisites;
		String regex = "(?<=[A-Za-z])(?=[0-9])";
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("course_name") + "\n");
				System.out.println(jsonObj.get("course_number") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				course.setType("Graduate");
				List<String> prerequisites = new ArrayList<String>();
				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				prerequisite = prerequisite.replaceAll(regex, " ");
				Optional<Courses> existingCourseOpt = response.stream()
						.filter(res -> res.getCourseName().equals(course.getCourseName())).findAny();
				if (existingCourseOpt.isPresent()) {
					Courses existingCourse = existingCourseOpt.get();
					if (!(prerequisite.equals("AD_CIT")) && !(prerequisite.equals("AD_SE"))
							&& !CollectionUtils.isEmpty(existingCourse.getPrerequisite())) {
						existingCourse.getPrerequisite().add(prerequisite);
					}
					// existingCourse.setPrerequisite(prerequisites);
				} else {
					if (prerequisite.equals("AD_CIT") || prerequisite.equals("AD_SE")) {
						prerequisite = "None";
					}
					prerequisites.add(prerequisite);
					hsetPrerequisites = new HashSet<String>(prerequisites);
					course.setPrerequisite(hsetPrerequisites);
					response.add(course);
				}
			}
		} finally {
			qexec.close();
		}
		return response;
	}

	public List<Courses> getListOfUnderGraduateCourses() throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> " +

				"JSON { \"course\": ?course ," + " \"courseName\" : ?course_name , "
				+ " \"courseNumber\" : ?course_number , " + " \"coursePrerequisite\" : ?prerequisite } " + "where {"
				+ "?course rdf:type AdvisoryBot:Undergraduate ."
				+ "OPTIONAL { ?course AdvisoryBot:hasPrerequisite ?prerequisite . }"
				+ "?course AdvisoryBot:courseName ?course_name ."
				+ "?course AdvisoryBot:courseNumber ?course_number .}";

		Model schema = schemaFactory.readCourseSchema();
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		HashSet<String> hsetPrerequisites;
		List<Courses> response = new ArrayList<Courses>();
		String regex = "(?<=[A-Za-z])(?=[0-9])";
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			// JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("Graduate courses");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("course_name") + "\n");
				System.out.println(jsonObj.get("course_number") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				Courses course = new Courses();
				course.setCourseName(jsonObj.get("course_name").toString().replace("\"", ""));
				course.setCourseNumber(jsonObj.get("course_number").toString().replace("\"", ""));
				course.setType("UnderGraduate");
				List<String> prerequisites = new ArrayList<String>();
				// course.setPrerequisite(jsonObj.get("prerequisite").toString().replace("\"",
				// ""));
				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				prerequisite = prerequisite.replaceAll(regex, " ");
				Optional<Courses> existingCourseOpt = response.stream()
						.filter(res -> res.getCourseName().equals(course.getCourseName())).findAny();
				if (existingCourseOpt.isPresent()) {
					Courses existingCourse = existingCourseOpt.get();
					if (!(prerequisite.equals("AD_CIT")) && !(prerequisite.equals("AD_SE"))
							&& !CollectionUtils.isEmpty(existingCourse.getPrerequisite())) {
						existingCourse.getPrerequisite().add(prerequisite);
					}
					// existingCourse.setPrerequisite(prerequisites);
				} else {
					if (prerequisite.equals("AD_CIT") || prerequisite.equals("AD_SE")) {
						prerequisite = "None";
					}
					prerequisites.add(prerequisite);
					hsetPrerequisites = new HashSet<String>(prerequisites);
					course.setPrerequisite(hsetPrerequisites);
					response.add(course);
				}
			}

		} finally {
			qexec.close();
		}
		return response;
	}

	public Courses getCourseDetails(String courseNumber) throws IOException {
		Model schema = schemaFactory.readIntegratedSchema();
		System.out.println("courseNumber " + courseNumber);
		Courses course = new Courses();

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> \r\n" + "\r\n"
				+ "JSON { \"course\": ?course ,\r\n" + " \"courseNumber\" : ?courseNumber ,\r\n"
				+ " \"courseName\" : ?courseName , \r\n" + " \"type\" : ?type ,\r\n"
				+ " \"coursePrerequisite\" : ?prerequisite ,\r\n" + " \"timings\" : ?timings ,\r\n"
				+ " \"requiredCourse\" : ?requiredCourse ,\r\n" + " \"days\" : ?days ,\r\n"
				+ " \"semesters\" : ?semesters ,\r\n" + " \"courseCRN\" : ?courseCRN ,\r\n"
				+ " \"courseDesc\" : ?courseDesc ,\r\n " + " \"faculty\" : ?faculty } \r\n" + " \r\n" + "where {\r\n"
				+ "?course AdvisoryBot:courseNumber \"" + courseNumber + "\" .\r\n"
				+ " ?course AdvisoryBot:courseName ?courseName .\r\n"
				+ " ?course AdvisoryBot:timeOffered ?timings .\r\n"
				+ " ?course AdvisoryBot:semesterOffered ?semesters .\r\n"
				+ " ?course AdvisoryBot:daysOffered ?days .\r\n" + " ?course AdvisoryBot:courseCRN ?courseCRN .\r\n"
				+ " OPTIONAL {?course AdvisoryBot:hasPrerequisite ?prerequisite .}\r\n"
				+ " OPTIONAL {?course AdvisoryBot:isRequiredCoreFor ?requiredCourse .}\r\n"
				+ " OPTIONAL {?course AdvisoryBot:courseDescription ?courseDesc .}\r\n"
				+ " ?course rdf:type ?type .\r\n" + "?faculty AdvisoryBot:teaches	?course ."
				+ " FILTER regex (str(?type), \"graduate\" , \"i\")\r\n" + "}";

		System.out.println("query:======================================= \n" + selectQuery1);

		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			// JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 3:");
			System.out.println("Courses Details");
			System.out.println("===============================================================================");
			List<String> prerequisites = new ArrayList<String>();
			List<String> days = new ArrayList<String>();
			List<String> semesters = new ArrayList<String>();
			List<String> timings = new ArrayList<String>();
			Faculty faculty = new Faculty();
			String regex = "(?<=[A-Za-z])(?=[0-9])";
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("courseName") + "\n");
				System.out.println(jsonObj.get("courseDesc") + "\n");
				System.out.println(jsonObj.get("courseNumber") + "\n");
				System.out.println(jsonObj.get("type") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				System.out.println(jsonObj.get("requiredCourse") + "\n");
				System.out.println(jsonObj.get("courseCRN") + "\n");
				System.out.println(jsonObj.get("days") + "\n");
				System.out.println(jsonObj.get("semester") + "\n");
				System.out.println(jsonObj.get("timings") + "\n");
				System.out.println(jsonObj.get("faculty") + "\n");
				String facultyName = jsonObj.get("faculty").toString().replace("\"", "");
				facultyName = facultyName.substring(facultyName.indexOf("#") + 1, facultyName.length());
				faculty.setName(facultyName);
				course.setTaughtBy(faculty);
				course.setCourseName(jsonObj.get("courseName").toString().replace("\"", ""));
				course.setCourseNumber(courseNumber);
				String type = jsonObj.get("type").toString().replace("\"", "");
				type = type.substring(type.indexOf("#") + 1, type.length());
				course.setType(type);
				course.setCourseDesc(jsonObj.get("courseDesc").toString().replace("\"", ""));
				// setting prerequisites

				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				if (!(prerequisite.equals("AD_CIT")) && !(prerequisite.equals("AD_SE"))) {
					prerequisite = prerequisite.replaceAll(regex, " ");
					prerequisites.add(prerequisite);
				}

				Programs program = new Programs();
				String requiredCourse = jsonObj.get("requiredCourse").toString().replace("\"", "");
				requiredCourse = requiredCourse.substring(requiredCourse.indexOf("#") + 1, requiredCourse.length());
				program.setProgramName(requiredCourse);
				course.setRequiredCourse(program);
				course.setCourseCRN(jsonObj.get("courseCRN").toString().replace("\"", ""));

				String day = jsonObj.get("days").toString().replace("\"", "");
				day = day.substring(day.indexOf("#") + 1, day.length());
				days.add(day);

				String semester = jsonObj.get("semesters").toString().replace("\"", "");
				semester = semester.substring(semester.indexOf("#") + 1, semester.length());
				semesters.add(semester);

				String timing = jsonObj.get("timings").toString().replace("\"", "");
				timing = timing.substring(timing.indexOf("#") + 1, timing.length());
				timings.add(timing);

			}
			HashSet<String> hSetPrerequisites = new HashSet<String>(prerequisites);
			HashSet<String> hSetdays = new HashSet<String>(days);
			HashSet<String> hSetSemesters = new HashSet<String>(semesters);
			HashSet<String> hSetTimings = new HashSet<String>(timings);

			course.setPrerequisite(hSetPrerequisites);
			course.setDays(hSetdays);
			course.setSemesters(hSetSemesters);
			course.setTimings(hSetTimings);

		} finally

		{
			qexec.close();
		}

		return course;

	}

	public List<Courses> getSchedule(String semester) throws IOException {
		Model schema = schemaFactory.readIntegratedSchema();
		List<Courses> courses = new ArrayList<Courses>();
		List<String> dayPart = Arrays.asList("Morning","Afternoon","Evening");
		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#>\r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl>\r\n" + "\r\n" + "JSON { \r\n"
				+ "\"course\": ?course , \r\n" + "\"courseNumber\" : ?courseNumber ,\r\n"
				+ "\"courseName\" : ?courseName , \r\n" + "\"type\" : ?type ,\r\n"
				+ "\"timings\" : ?timings ,\r\n"
				+ "\"days\" : ?days ,\r\n"
				+ "\"semesters\" : ?semesters ,\r\n"
				+ "\"faculty\" : ?faculty \r\n" + "}\r\n" 
				+ "where {\r\n"
				+ "?course AdvisoryBot:courseNumber ?courseNumber .\r\n"
				+ "?course AdvisoryBot:courseName ?courseName .\r\n" + "?course AdvisoryBot:timeOffered ?timings .\r\n"
				+ "?course AdvisoryBot:semesterOffered ?semesters .\r\n" + "?course AdvisoryBot:daysOffered ?days .\r\n"
				+ "?course rdf:type ?type .\r\n"
				+ "?faculty :teaches ?course ."
				+ "FILTER regex (str(?type), \"graduate\" , \"i\")\r\n" + "}\r\n";

		System.out.println("query:======================================= \n" + selectQuery1);

		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			// JsonArray QueryExecution.execJson()
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 3:");
			System.out.println("Courses timings");
			System.out.println("===============================================================================");
			String regex = "(?<=[A-Za-z])(?=[0-9])";
			while (json.hasNext()) {
				Faculty faculty = new Faculty();
				Courses course = new Courses();
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("course") + "\n");
				System.out.println(jsonObj.get("courseName") + "\n");
				System.out.println(jsonObj.get("courseDesc") + "\n");
				System.out.println(jsonObj.get("courseNumber") + "\n");
				System.out.println(jsonObj.get("type") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");
				System.out.println(jsonObj.get("requiredCourse") + "\n");
				System.out.println(jsonObj.get("courseCRN") + "\n");
				System.out.println(jsonObj.get("days") + "\n");
				System.out.println(jsonObj.get("semesters") + "\n");
				System.out.println(jsonObj.get("timings") + "\n");
				System.out.println(jsonObj.get("faculty") + "\n");
				String semesterString = jsonObj.get("semesters").toString().replace("\"", "");
				semesterString = semesterString.substring(semesterString.indexOf("#") + 1, semesterString.length());
				if(semester == null) {
					semester = "Spring";
				}
				if(semester.equalsIgnoreCase(semesterString)) {
					String courseNumber = jsonObj.get("courseNumber").toString().replace("\"", "");
					String facultyName = jsonObj.get("faculty").toString().replace("\"", "");
					facultyName = facultyName.substring(facultyName.indexOf("#") + 1, facultyName.length());
					course.setCourseName(jsonObj.get("courseName").toString().replace("\"", ""));

					Optional<Courses> courseOptional = courses.stream().filter(e -> e.getCourseNumber().equals(courseNumber)).findAny();
					if(courseOptional.isPresent()) {
						course = courseOptional.get();
					}
					else {
						course.setCourseNumber(courseNumber);
						String type = jsonObj.get("type").toString().replace("\"", "");
						type = type.substring(type.indexOf("#") + 1, type.length());
						course.setType(type);
						faculty.setName(facultyName);
						course.setTaughtBy(faculty);
						courses.add(course);
					}

					mapDay(course, jsonObj);

					mapTimings(dayPart, course, jsonObj);

					mapSemester(course, jsonObj);
				}
			}
		} finally {
			qexec.close();
		}
		return courses;

	}

	private void mapSemester(Courses course, JsonObject jsonObj) {
		String semester = jsonObj.get("semesters").toString().replace("\"", "");
		semester = semester.substring(semester.indexOf("#") + 1, semester.length());
		if(null == course.getSemesters()) {
			HashSet<String> semesters = new HashSet<String>();
			semesters.add(semester);
			course.setSemesters(semesters);
		}
		else {
			course.getSemesters().add(semester);
		}
	}

	private void mapDay(Courses course, JsonObject jsonObj) {
		String day = jsonObj.get("days").toString().replace("\"", "");
		day = day.substring(day.indexOf("#") + 1, day.length());
		if(null == course.getDays()) {
			HashSet<String> days = new HashSet<String>();
			days.add(day);
			course.setDays(days);
		}
		else {
			course.getDays().add(day);
		}
	}

	private void mapTimings(List<String> dayPart, Courses course, JsonObject jsonObj) {
		String timing = jsonObj.get("timings").toString().replace("\"", "");
		timing = timing.substring(timing.indexOf("#") + 1, timing.length());
		if(dayPart.contains(timing)) {
			if(null == course.getPartOfDay()) {
				HashSet<String> partOfDay = new HashSet<String>();
				partOfDay.add(timing);
				course.setPartOfDay(partOfDay);
			}
			else {
				course.getPartOfDay().add(timing);
			}
		}
		else {
			if(null ==  course.getTimings()) {
				HashSet<String> timings = new HashSet<String>();
				timings.add(timing);
				course.setTimings(timings);
			}
			else {
				course.getTimings().add(timing);
			}
		}
	}

}