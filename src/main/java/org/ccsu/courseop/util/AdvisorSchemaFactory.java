package org.ccsu.courseop.util;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.reasoner.Reasoner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import openllet.jena.PelletReasonerFactory;

@Component
public class AdvisorSchemaFactory {

	private static final Logger logger = LogManager.getLogger(AdvisorSchemaFactory.class);

	private static InfModel courseInference;
	private static InfModel facultyInference;
	private static InfModel unionInference;
	
	@Value("classpath:static/StudentProg-3.1.ttl")
	private Resource coursesResource;
	
	@Value("classpath:static/Faculty-me.ttl")
	 private Resource facultyResource;

	
	private OntModel courseSchema = ModelFactory.createOntologyModel();
	private OntModel facultySchema = ModelFactory.createOntologyModel();
	private Model unionSchema = ModelFactory.createOntologyModel();

	private Reasoner reasoner = PelletReasonerFactory.theInstance().create();

	private String base = "http://www.cs.ccsu.edu/~neli/AdvisoryBot.owl#";

	@PostConstruct
	public void schemaGenerator() throws IOException {
		// course Schema
		courseSchema.read(coursesResource.getInputStream(), null, "TTL");
		courseInference = ModelFactory.createInfModel(reasoner, courseSchema);
		
		//faculty Schema
		facultySchema.read(facultyResource.getInputStream(), null, "TTL");
		facultyInference = ModelFactory.createInfModel(reasoner, facultySchema);
		
		//union Schema
		facultySchema.read(facultyResource.getInputStream(), null, "TTL");
		courseSchema.read(coursesResource.getInputStream(), null, "TTL");

		Property teaches = facultySchema.createProperty(base + "teaches");

		Individual profChad = facultySchema.getIndividual(base + "Chad_Williams");
		Individual course502 = courseSchema.getIndividual(base + "CS502");
		Individual course505 = courseSchema.getIndividual(base + "CS505");
		Individual course492 = courseSchema.getIndividual(base + "CS492");

		profChad.addProperty(teaches, course502);
		profChad.addProperty(teaches, course505);
		profChad.addProperty(teaches, course492);

		Individual FatemehAbdollahzadeh = facultySchema.getIndividual(base + "Fatemeh_Abdollahzadeh");
		Individual course500 = courseSchema.getIndividual(base + "CS500");
		Individual course501 = courseSchema.getIndividual(base + "CS501");
		Individual course464 = courseSchema.getIndividual(base + "CS464");
		Individual course481 = courseSchema.getIndividual(base + "CS481");

		FatemehAbdollahzadeh.addProperty(teaches, course500);
		FatemehAbdollahzadeh.addProperty(teaches, course501);
		FatemehAbdollahzadeh.addProperty(teaches, course464);
		FatemehAbdollahzadeh.addProperty(teaches, course481);

		Individual YusufAlbayram = facultySchema.getIndividual(base + "Yusuf_Albayram");
		Individual course550 = courseSchema.getIndividual(base + "CS550");
		Individual course560 = courseSchema.getIndividual(base + "CS560");
		Individual course506 = courseSchema.getIndividual(base + "CS506");
		Individual course416 = courseSchema.getIndividual(base + "CS416");

		YusufAlbayram.addProperty(teaches, course550);
		YusufAlbayram.addProperty(teaches, course560);
		YusufAlbayram.addProperty(teaches, course506);
		YusufAlbayram.addProperty(teaches, course416);

		Individual StanKukovsky = facultySchema.getIndividual(base + "Stan_Kurkovsky");
		Individual course510 = courseSchema.getIndividual(base + "CS510");
		Individual course530 = courseSchema.getIndividual(base + "CS530");
		Individual course410 = courseSchema.getIndividual(base + "CS410");
		Individual course498 = courseSchema.getIndividual(base + "CS498");
		Individual course455 = courseSchema.getIndividual(base + "CS455");

		StanKukovsky.addProperty(teaches, course510);
		StanKukovsky.addProperty(teaches, course530);
		StanKukovsky.addProperty(teaches, course410);
		StanKukovsky.addProperty(teaches, course498);
		StanKukovsky.addProperty(teaches, course455);

		Individual NeliZlatareva = facultySchema.getIndividual(base + "Neli_Zlatareva");
		Individual course575 = courseSchema.getIndividual(base + "CS575");
		Individual course570 = courseSchema.getIndividual(base + "CS570");
		Individual course253 = courseSchema.getIndividual(base + "CS253");
		Individual course407 = courseSchema.getIndividual(base + "CS407");
		Individual course590 = courseSchema.getIndividual(base + "CS590");

		NeliZlatareva.addProperty(teaches, course575);
		NeliZlatareva.addProperty(teaches, course570);
		NeliZlatareva.addProperty(teaches, course253);
		NeliZlatareva.addProperty(teaches, course407);
		NeliZlatareva.addProperty(teaches, course590);

		Individual BradleyKjell = facultySchema.getIndividual(base + "Bradley_Kjell");
		Individual course152 = courseSchema.getIndividual(base + "CS152");
		Individual course254 = courseSchema.getIndividual(base + "CS254");
		Individual course494 = courseSchema.getIndividual(base + "CS494");
		Individual course595 = courseSchema.getIndividual(base + "CS595");

		BradleyKjell.addProperty(teaches, course152);
		BradleyKjell.addProperty(teaches, course254);
		BradleyKjell.addProperty(teaches, course494);
		BradleyKjell.addProperty(teaches, course595);

		Individual IrenaPevac = facultySchema.getIndividual(base + "Irena_Pevac");
		Individual course580 = courseSchema.getIndividual(base + "CS580");
		Individual course151 = courseSchema.getIndividual(base + "CS151");
		Individual course460 = courseSchema.getIndividual(base + "CS460");

		IrenaPevac.addProperty(teaches, course580);
		IrenaPevac.addProperty(teaches, course151);
		IrenaPevac.addProperty(teaches, course460);

		Individual SixiaChen = facultySchema.getIndividual(base + "Sixia_Chen");
		Individual course525 = courseSchema.getIndividual(base + "CS525");
		Individual course508 = courseSchema.getIndividual(base + "CS508");
		Individual course463 = courseSchema.getIndividual(base + "CS463");

		SixiaChen.addProperty(teaches, course525);
		SixiaChen.addProperty(teaches, course508);
		SixiaChen.addProperty(teaches, course463);

		Individual ThomasKing = facultySchema.getIndividual(base + "Tom_King");
		Individual course291 = courseSchema.getIndividual(base + "CS291");

		ThomasKing.addProperty(teaches, course291);

		Individual ZdravkoMarkov = facultySchema.getIndividual(base + "Zdravko_Markov");
		Individual course354 = courseSchema.getIndividual(base + "CS354");
		Individual course385 = courseSchema.getIndividual(base + "CS385");
		Individual course565 = courseSchema.getIndividual(base + "CS565");

		ZdravkoMarkov.addProperty(teaches, course354);
		ZdravkoMarkov.addProperty(teaches, course385);
		ZdravkoMarkov.addProperty(teaches, course565);

		Individual MahiehZabihimayvan = facultySchema.getIndividual(base + "Mahdieh_Zabihimayvan");
		Individual course355 = courseSchema.getIndividual(base + "CS355");
		Individual course493 = courseSchema.getIndividual(base + "CS493");

		MahiehZabihimayvan.addProperty(teaches, course355);
		MahiehZabihimayvan.addProperty(teaches, course493);

		facultySchema.write(System.out, "TURTLE");
		courseSchema.write(System.out, "TURTLE");
		
		unionSchema = facultySchema.union(courseSchema);
		unionInference = ModelFactory.createInfModel(reasoner, unionSchema);
		unionInference.validate();
		
		System.out.println("UNION INFERENCE===============================================");
		unionInference.write(System.out, "TURTLE");

	}
	
	
	public Model readCourseSchema() throws IOException {
		return courseInference;
	}

	public Model readFacultySchema() throws IOException {
		return facultyInference;
	}

	public Model readIntegratedSchema() throws IOException {
		return unionInference;
	}

}
