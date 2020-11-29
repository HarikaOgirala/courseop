package org.ccsu.courseop.model;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Courses {

	private String courseName;
	private String courseNumber;
	private String type;
	private HashSet<String> prerequisite;
	private HashSet<String> timings;
	private HashSet<String> days;
	private HashSet<String> semesters;
	private String courseCRN;
	private Programs requiredCourse;
	private String courseDesc;
	private Faculty taughtBy;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HashSet<String> getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(HashSet<String> prerequisite) {
		this.prerequisite = prerequisite;
	}
	public HashSet<String> getTimings() {
		return timings;
	}
	public void setTimings(HashSet<String> timings) {
		this.timings = timings;
	}
	public HashSet<String> getDays() {
		return days;
	}
	public void setDays(HashSet<String> days) {
		this.days = days;
	}
	public HashSet<String> getSemesters() {
		return semesters;
	}
	public void setSemesters(HashSet<String> semesters) {
		this.semesters = semesters;
	}
	public String getCourseCRN() {
		return courseCRN;
	}
	public void setCourseCRN(String courseCRN) {
		this.courseCRN = courseCRN;
	}
	public Programs getRequiredCourse() {
		return requiredCourse;
	}
	public void setRequiredCourse(Programs requiredCourse) {
		this.requiredCourse = requiredCourse;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public Faculty getTaughtBy() {
		return taughtBy;
	}
	public void setTaughtBy(Faculty taughtBy) {
		this.taughtBy = taughtBy;
	}
}
