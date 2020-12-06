package org.ccsu.courseop.model;

import java.util.HashSet;

public class Programs {
	
	private String programName;
	private HashSet<String> requiredCourses;
	private int totalCredits;
	private String admissionRequirements;
	private HashSet<String> electives;
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public HashSet<String> getRequiredCourses() {
		return requiredCourses;
	}
	public void setRequiredCourses(HashSet<String> requiredCourses) {
		this.requiredCourses = requiredCourses;
	}
	public int getTotalCredits() {
		return totalCredits;
	}
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}
	public String getAdmissionRequirements() {
		return admissionRequirements;
	}
	public void setAdmissionRequirements(String admissionRequirements) {
		this.admissionRequirements = admissionRequirements;
	}
	public HashSet<String> getElectives() {
		return electives;
	}
	public void setElectives(HashSet<String> electives) {
		this.electives = electives;
	}
}
