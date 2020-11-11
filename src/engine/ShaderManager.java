package engine;

import java.util.HashMap;

import renderer.ShaderProgram;
import renderer.SpriteShader;

public class ShaderManager {
	private HashMap<String, ShaderProgram> shaders;
	private String filepath;
	
	public ShaderManager() {
		shaders = new HashMap<String, ShaderProgram>();
	}
	
	public void load(String filepath) {
		this.filepath = filepath;
		ShaderProgram defaultSprite = new SpriteShader();
		shaders.put("-spriteDefault", defaultSprite);
	}
	
	public void clear() {
		shaders.clear();
	}
	
	public ShaderProgram get(String name) {
		if(shaders.containsKey(name)) return shaders.get(name);
		ShaderProgram prog = new ShaderProgram(filepath + "/shaders/" + name + "/vert.txt", filepath + "/shaders/" + name + "/frag.txt");
		shaders.put(name, prog);
		return prog;
	}
	
}
