package org.ccsu.courseop.model;

import org.springframework.stereotype.Component;

@Component
public class Timings {
	
	private String course;
	private String courseNumber;
	private String courseName;
	private String type;
	private String prerequisite;
	private String timings;
	private String requiredCourse;
	private String days;
	private String semesters;
	private String courseCRN;
	private String courseDesc;
	private String faculty;
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public String getRequiredCourse() {
		return requiredCourse;
	}
	public void setRequiredCourse(String requiredCourse) {
		this.requiredCourse = requiredCourse;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSemesters() {
		return semesters;
	}
	public void setSemesters(String semesters) {
		this.semesters = semesters;
	}
	public String getCourseCRN() {
		return courseCRN;
	}
	public void setCourseCRN(String courseCRN) {
		this.courseCRN = courseCRN;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

}
