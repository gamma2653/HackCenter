package com.gamsion.chris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DatabaseControl {
	private Hackathon hackathon;
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
		char c = string.charAt(0);
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
					System.out.println(line);
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
				br.close();
				return hackathon;

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		return null;


	}

	public void addHashMapStyle(String whatType, String name, HashMap<String,String> info,File file){
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


		hackathonToFile(hackathon,file);





	}

	public void changeNameOrDescription(String whatType, String what,File file){
		Hackathon hackathon = Parse(file);
		if(whatType.toLowerCase().equals("name")){
			hackathon.name = what;

		}


		else if(whatType.toLowerCase().equals("description")){
			hackathon.description = what;
		}

		hackathonToFile(hackathon,file);
	}



	public void hackathonToFile(Hackathon hackathon,File file){
		try {
			BufferedWriter BW = new BufferedWriter(new FileWriter(file));
			BW.write("name:" + hackathon.name);
			BW.newLine();
			BW.write("description:" + hackathon.description);
			BW.newLine();

			BW.write("organizers:");
			int i = 0;
			for(String key : hackathon.organizers.keySet()){
				BW.write(key + "(");
				for(String key2 : hackathon.organizers.get(key).keySet()){
					if(i < hackathon.organizers.keySet().size() - 1){
						BW.write(key2 + ":" + hackathon.organizers.get(key).get(key2) + ",");

					}
					else{
						BW.write(key2 + ":" + hackathon.organizers.get(key).get(key2));
					}
				}
				i++;
				BW.write(")");
			}
			BW.newLine();
			BW.write("students:");
			i = 0;
			for(String key : hackathon.students.keySet()){
				BW.write(key + "(");

				for(String key2 : hackathon.students.get(key).keySet()){
					if(i < hackathon.students.keySet().size() - 1){
						BW.write(key2 + ":" + hackathon.students.get(key).get(key2) + ",");

					}
					else{
						BW.write(key2 + ":" + hackathon.students.get(key).get(key2));
					}
				}
				i++;
				BW.write(")");
			}
			BW.newLine();


			i = 0;
			for(String key : hackathon.events.keySet()){
				BW.write(key + "(");
				for(String key2 : hackathon.events.get(key).keySet()){
					BW.write(key2 + ":" + hackathon.events.get(key).get(key2) + ",");
					if(i < hackathon.events.keySet().size() - 1){
						BW.write(key2 + ":" + hackathon.events.get(key).get(key2) + ",");

					}
					else{
						BW.write(key2 + ":" + hackathon.events.get(key).get(key2));
					}
				}
				BW.write(")");
			}
			BW.newLine();
			BW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHashMapStyle(String type,String key1, String key2,File file){
		Hackathon hackathon = Parse(file);
		if(type.toLowerCase().equals("organizers")){
			return hackathon.organizers.get(key1).get(key2);

		}

		else if(type.toLowerCase().equals("students")){
			System.out.println(hackathon.students);
			return hackathon.students.get(key1).get(key2);

		}

		else if(type.toLowerCase().equals("events")){
			return hackathon.events.get(key1).get(key2);

		}
		return null;


	}

	public String getStringStyle(String key,File file){
		Hackathon hackathon = Parse(file);
		if(key.toLowerCase().equals("name")){
			return hackathon.name;
		}

		else if(key.toLowerCase().equals("description")){
			return hackathon.description;
		}
		return null;

	}


	
	public static void main(String[] args){
		DatabaseControl dc = new DatabaseControl();
		File file = new File("C:/Users/sophia/Downloads/file.txt");
		File file2 = new File("C:/Users/sophia/Desktop/file2.txt");
		Hackathon hackathon = dc.Parse(file);
		System.out.println(hackathon.events);
		dc.hackathonToFile(hackathon, file2);
		System.out.println(dc.getHashMapStyle("Students","Sophia Roshal", "shirt", file));

	}









}
