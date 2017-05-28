package com.gamsion.chris;

import java.util.HashMap;

import javax.management.RuntimeErrorException;

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
	

	
	
	
	
	

}
