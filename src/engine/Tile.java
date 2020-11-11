package engine;

import java.util.ArrayList;

public class Tile {
	
	public int id;
	public String sprite, name;
	public ArrayList<String> flags;
	
	public boolean render;
	
	
	Tile(int id, String name, String sprite) {
		this.id = id;
		this.name = name;
		this.sprite = sprite;
		flags = new ArrayList<String>();
		render = true;
	}
	
	public void addFlag(String flag) {
		if(flag.equals("-noRender")) {
			render = false;
		}
		
		flags.add(flag);
	}
	
	public boolean hasFlag(String flag) {
		for(String str : flags) if(flag.equals(str)) return true;
		return false;
	}

}
