package engine;

import java.util.ArrayList;
import java.util.HashMap;

import json.JSON;
import json.JSONArr;


public class Tilemap {
	
	public ArrayList<Tile> tiles;
	private HashMap<String, Integer> map;
	private int defaultTile;
	
	public Tilemap() {
		tiles = new ArrayList<Tile>();
		map = new HashMap<String, Integer>();
	}
	
	public void load(JSON json) {
		
		// Load tiles
		JSONArr tileDat = json.getArr("tiles");
		
		for(int i = 0; i < tileDat.len(); i ++) {
			JSON data = tileDat.getJSON(i);
			Tile t = new Tile((int)data.getLong("id"), data.getStr("name"), data.getStr("sprite"));
			JSONArr flags = data.getArr("flags");
			
			
			for(int j = 0; j < flags.len(); j ++) {
				t.addFlag(flags.getStr(j));
			}
			
			tiles.add(t);
			
		}
		
		defaultTile = (int) json.getLong("defaultTile");
		
		// Load map data
		JSON mapData = json.getJSON("map");
		for(String coord : mapData.keys()) {
			int tile = (int) mapData.getLong(coord);
			map.put(coord, tile);
		}
	}
	
	public int getTile(int x, int y) {
		String coord = x + "," + y;
		if(map.containsKey(coord)) return map.get(coord);
		return defaultTile;
	}
	
}
