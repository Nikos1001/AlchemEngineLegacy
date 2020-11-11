package engine;

import java.util.HashMap;

import renderer.Texture;

public class TextureManager {

	private HashMap<String, Texture> textures;
	private String filepath;
	
	public TextureManager() {
		textures = new HashMap<String, Texture>();
	}
	
	public void load(String filepath) {
		this.filepath = filepath;
		Texture blank = new Texture("res/white.png");
		textures.put("-blank", blank);
	}
	
	public void clear() {
		textures.clear();
	}
	
	public Texture get(String name) {
		if(textures.containsKey(name)) return textures.get(name);
		Texture tex = new Texture(filepath + "/sprites/" + name);
		textures.put(name, tex);
		return tex;
	}
	
}
