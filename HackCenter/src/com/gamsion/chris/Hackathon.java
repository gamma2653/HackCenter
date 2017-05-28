package com.gamsion.chris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hackathon {
	String name;
	String description;
	Map<String, Map<String, String>> organizers = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> students =  new HashMap<String, Map<String, String>>();
	List<Event> events = new ArrayList<Event>();

	public Hackathon(String name, String description, Map<String, Map<String, String>> organizers, Map<String, Map<String, String>> students, List<Event> events){
		this.name = name;
		this.description = description;
		this.organizers = organizers;
		this.students = students;
		this.events = events;
	}
	public class Event{
		
		String name;
		String description;
		String time;
		
		public Event(String name, String description, String time){
			this.name = name;
			this.description = description;
			this.time = time;
		}
	}

}
