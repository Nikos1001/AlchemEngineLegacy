package engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.ParseException;

import json.JSON;

public class JSONManager {

	private HashMap<String, JSON> data;
	private String filepath;
	
	public JSONManager() {
		data = new HashMap<String, JSON>();
	}
	
	public void load(String filepath) {
		this.filepath = filepath;
	}
	
	public void clear() {
		data.clear();
	}
	
	public JSON get(String name) throws FileNotFoundException, IOException, ParseException {
		if(data.containsKey(name)) return data.get(name);
		JSON json = new JSON(filepath + "/data/" + name);
		data.put(name, json);
		return json;
	}
	
}
