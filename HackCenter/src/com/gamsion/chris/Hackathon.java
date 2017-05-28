package com.gamsion.chris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hackathon {
	public String name;
	public String description;
	public HashMap<String, HashMap<String, String>> organizers = new HashMap<String, HashMap<String, String>>();
	public HashMap<String, HashMap<String, String>> students =  new HashMap<String, HashMap<String, String>>();
	public HashMap<String, HashMap<String, String>> events = new HashMap<String, HashMap<String, String>>();

	public Hackathon(String name, String description, HashMap<String, HashMap<String, String>> organizers, HashMap<String, HashMap<String, String>> students, HashMap<String, HashMap<String, String>> events){
		this.name = name;
		this.description = description;
		this.organizers = organizers;
		this.students = students;
		this.events = events;
	}
	/*public class Event{
		
		String name;
		String description;
		String time;
		
		public Event(String name, String description, String time){
			this.name = name;
			this.description = description;
			this.time = time;
		
	}*/

}
