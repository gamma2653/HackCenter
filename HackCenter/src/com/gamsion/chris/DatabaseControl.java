package com.gamsion.chris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
		String name = "";
		while(c != '('){
			name += c;
			index ++;
			c = s.charAt(index);

		}

		HashMap<String,String> info = new HashMap<String,String>();

		while(c!= ')'){
			String key = "";
			index ++;
			c = s.charAt(index);
			while(c != ':'){
				key += c;
				index ++;
				c = s.charAt(index);
			}
			

			String value = "";
			index ++;
			c = s.charAt(index);
			while(c != ',' && c != ')'){
				value += c;
				index ++;
				c = s.charAt(index);
			}

			info.put(key, value);

		}
		names.put(name, info);


		return names;
	}
	
	public String returnString(String stringName){
		return stringName;
		
	}
	
	public Hackathon Parse(File file){

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			StringBuilder sb = new StringBuilder();
			try {
				while ((line = br.readLine())!=null){
					sb.append(line+"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return null;
	
		
	}
	

	
	
	
	
	

}
