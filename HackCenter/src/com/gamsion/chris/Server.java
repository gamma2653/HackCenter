package com.gamsion.chris;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;
public class Server {
	public static void main(String[] args) {
		post("/login", (req, res) -> {
			System.out.println(req.body());
			System.out.println(stringToMap(req.body()));
			return "";
		});
	}
	private static Map<String, String> stringToMap(String in){
		Map<String, String> map = new HashMap<String, String>();
		for(String s: in.split("&")){
			String[] pair = s.split("=");
			if(pair.length!=2){
				throw new RuntimeException("There is not a pair.");
			}
			map.put(pair[0], pair[1]);
		}
		return map;
	}
}
