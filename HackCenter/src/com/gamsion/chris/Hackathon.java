package com.gamsion.chris;

import java.util.HashMap;
import java.util.Map;

public class Hackathon {
	String name;
	String description;
	Map<String, Map<String, String>> organizers = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> students =  new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> events = new HashMap<String, Map<String, String>>();

	public Hackathon(String name, String description, Map<String, Map<String, String>> organizers, Map<String, Map<String, String>> students, Map<String, Map<String, String>> events){
		this.name = name;
		this.description = description;
		this.organizers = organizers;
		this.students = students;
		this.events = events;
	}

}
