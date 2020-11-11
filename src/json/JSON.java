package json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON {
	
	JSONObject obj;
	
	public JSON() {
		obj = new JSONObject();
	}
	
	public JSON(String filepath) throws FileNotFoundException, IOException, ParseException {
		obj = (JSONObject)(new JSONParser().parse(new FileReader(filepath)));
	}
	
	public String getStr(String key) {
		return (String) obj.get(key);
	}
	
	public long getLong(String key) {
		return (long) obj.get(key);
	}
	
	public double getDouble(String key) {
		try {
			return getLong(key);
		} catch(ClassCastException exception) {
			
		}
		return (double) obj.get(key);
	}
	
	public JSON getJSON(String key) {
		JSONObject json = (JSONObject) obj.get(key);
		JSON newObj = new JSON();
		newObj.obj = json;
		return newObj;
	}
	
	public boolean getBool(String key) {
		return (boolean) obj.get(key);
	}
	
	public JSONArr getArr(String key) {
		JSONArr arr = new JSONArr();
		arr.arr = (JSONArray) obj.get(key);
		return arr;
	}
	
	public String[] keys() {
		Set<String> names = obj.keySet();
		String[] arr = new String[names.size()];
		int i = 0;
		for (String name : names) {
			arr[i++] = name;
		}
		return arr;
	}

}
