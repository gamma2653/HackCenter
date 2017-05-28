package com.gamsion.chris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseControl {
	public HashMap<String,HashMap<String,String>> parseString(String s){

		HashMap<String,HashMap<String,String>> names = new HashMap<String, HashMap<String,String>>();
		char c = s.charAt(0);


		String title = "";
		int index = 0;
		while(c != ':'){

			title += c;
			index ++;
			c = s.charAt(index);
		}
		index ++;
		c= s.charAt(index);
		String passedTitle = s.substring(index,s.length());
		
		String[] allInList = passedTitle.split(";");
		
		for(String str : allInList){
			
			String name = "";
			index = 0;
			c = str.charAt(index);
			while(c != '('){
				name += c;
				index ++;
				c = str.charAt(index);

			}
			

			HashMap<String,String> info = new HashMap<String,String>();

			while(c!= ')'){
				String key = "";
				index ++;
				c = str.charAt(index);
				while(c != ':'){
					key += c;
					index ++;
					c = str.charAt(index);
				}


				String value = "";
				index ++;
				c = str.charAt(index);
				while(c != ',' && c != ')'){
					value += c;
					index ++;
					c = str.charAt(index);
				}

				info.put(key, value);

			}
			names.put(name, info);

		}
		return names;
	}

	public String returnString(String string){
		String title = "";
		int index = 0;
		char c= string.charAt(0);
		while(c != ':' && c != '\n'){
			title += c;
			index ++;
			c = string.charAt(index);
		}
		String name = string.substring(index + 1, string.length());
		return name;
	}

	public String getTitle(String string){
		String title = "";
		int index = 0;
		char c= string.charAt(0);
		while(c != ':' && c != '\n'){
			title += c;
			index ++;
			c = string.charAt(index);
		}
		return title;

	}

	public Hackathon Parse(File file){

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			String name = "";
			String description = "";
			HashMap<String, HashMap<String, String>> organizers = new HashMap<String, HashMap<String, String>>();
			HashMap<String, HashMap<String, String>> students =  new HashMap<String, HashMap<String, String>>();
			HashMap<String, HashMap<String, String>> events = new HashMap<String, HashMap<String, String>>();

			try {
				while ((line = br.readLine())!=null){
					if(getTitle(line).toLowerCase().equals("organizers")){
						organizers = parseString(line);

					}

					else if(getTitle(line).toLowerCase().equals("students")){
						students = parseString(line);
					}

					else if(getTitle(line).toLowerCase().equals("events")){
						events = parseString(line);
					}

					else if(getTitle(line).toLowerCase().equals("name")){
						name = returnString(line);
					}

					else if(getTitle(line).toLowerCase().equals("description")){
						description = returnString(line);
					}

					sb.append(line +"\n");
				}
				Hackathon hackathon = new Hackathon(name,description,organizers,students,events);
				return hackathon;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		return null;


	}
	
	public Hackathon add(String whatType, String name, HashMap<String,String> info,File file){
		Hackathon hackathon = Parse(file);
		if(whatType.toLowerCase().equals("organizers")){
			hackathon.organizers.put(name, info);

		}

		else if(whatType.toLowerCase().equals("students")){
			hackathon.students.put(name, info);
		}

		else if(whatType.toLowerCase().equals("events")){
			hackathon.events.put(name, info);
		}

		else if(whatType.toLowerCase().equals("description")){
			
		}
		
		return hackathon;

		
	}
	
	
		
		
		
		
	
	public static void main(String[] args){
		DatabaseControl dc = new DatabaseControl();
		File file = new File("C:/Users/sophia/Desktop/file.txt");
		System.out.println(dc.Parse(file).events);

	}









}
