package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONArr {

	public JSONArray arr;
	
	public JSONArr() {
		arr = new JSONArray();
	}

	public String getStr(int i) {
		return (String) arr.get(i);
	}
	
	public JSON getJSON(int i) {
		JSONObject obj = (JSONObject) arr.get(i);
		JSON json = new JSON();
		json.obj = obj;
		return json;
	}
	
	public int len() {
		return arr.size();
	}
	
}
