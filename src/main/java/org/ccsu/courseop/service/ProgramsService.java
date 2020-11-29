package org.ccsu.courseop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.ccsu.courseop.model.Programs;
import org.ccsu.courseop.util.AdvisorSchemaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramsService {

	@Autowired
	private AdvisorSchemaFactory schemaFactory;
	QueryExecution qexec;
	Query query;

	/**
	 * SELECT QUERY 1: Program CS
	 * 
	 * @throws IOException
	 */
	public Programs getProgramInformation(String name) throws IOException {

		String selectQuery1 = "prefix : <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "prefix owl: <http://www.w3.org/2002/07/owl#> \r\n"
				+ "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
				+ "prefix xml: <http://www.w3.org/XML/1998/namespace> \r\n"
				+ "prefix xsd: <http://www.w3.org/2001/XMLSchema#> \r\n"
				+ "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
				+ "prefix AdvisoryBot: <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#> \r\n"
				+ "base <http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl> \r\n" + "\r\n"
				+ "JSON { \"program\": ?program ,\r\n" + "\"admissionRequirements\" : ?admissionRequirements , \r\n"
				+ "\"numberCredits\" : ?numberCredits ,\r\n" + "\"coursePrerequisite\" : ?prerequisite } \r\n"
				+ "where { \r\n"
				+ " AdvisoryBot:"+ name + " rdf:type owl:NamedIndividual .\r\n"
				+ "?program rdf:type AdvisoryBot:GraduateProgram.\r\n"
				+ "?program AdvisoryBot:programAdmissionRequirements ?admissionRequirements .\r\n"
				+ "?program AdvisoryBot:numberCredits ?numberCredits .\r\n"
				+ "?program ?courseRequired ?prerequisite .\r\n"
				+ "FILTER ( ?courseRequired = AdvisoryBot:hasRequiredCore || AdvisoryBot:hasRecommendedCore || AdvisoryBot:hasPrerequisite  )\r\n"
				+ "\r\n" + "}";

		Model schema = schemaFactory.readCourseSchema();
		System.out.println(selectQuery1);
		query = QueryFactory.create(selectQuery1, Syntax.syntaxARQ);
		qexec = QueryExecutionFactory.create(query, schema);
		Programs program = new Programs();
		String regex = "(?<=[A-Za-z])(?=[0-9])";
		if (name.equals("CIT_CS")) {
			program.setProgramName("CIT_CS");
		}
		else {
			program.setProgramName("CIT_SE");
		}
		List<String> requiredCourses = new ArrayList<String>();
		try {
			Iterator<JsonObject> json = qexec.execJsonItems();
			System.out.println("===============================================================================");
			System.out.println("SELECT QUERY 1 :");
			System.out.println("");
			System.out.println("===============================================================================");
			while (json.hasNext()) {
				JsonObject jsonObj = json.next();
				System.out.println("===================================");
				System.out.println(jsonObj.get("program") + "\n");
				System.out.println(jsonObj.get("admissionRequirements") + "\n");
				System.out.println(jsonObj.get("numberCredits") + "\n");
				System.out.println(jsonObj.get("prerequisite") + "\n");

				program.setAdmissionRequirements(jsonObj.get("admissionRequirements").toString().replace("\"", ""));
				program.setTotalCredits(Integer.parseInt(jsonObj.get("numberCredits").toString().replace("\"", "")));

				String prerequisite = jsonObj.get("prerequisite").toString().replace("\"", "");
				prerequisite = prerequisite.substring(prerequisite.indexOf("#") + 1, prerequisite.length());
				if(!requiredCourses.contains(prerequisite)) {
					prerequisite = prerequisite.replaceAll(regex, " ");
					requiredCourses.add(prerequisite);
				}
			}
			program.setRequiredCourses(requiredCourses);
		} finally {
			qexec.close();
		}
		return program;
	}
}
