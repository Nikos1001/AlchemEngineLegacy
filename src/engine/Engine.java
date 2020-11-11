package engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.python.core.PyFloat;
import org.python.util.PythonInterpreter;

import json.JSON;
import renderer.QuadRenderer;
import renderer.Renderer;
import renderer.ShaderProgram;
import renderer.SpriteShader;
import renderer.WindowManager;

public class Engine {

	private JSON config;
	private JSON windowConf;
	private String filepath;
	private PythonInterpreter interp;
	private double prevTime;
	
	public ShaderProgram spriteShader;
	
	public TextureManager textures;
	public JSONManager data;
	public ShaderManager shaders;
	
	public Context ctx;
	public String gameName;
	
	public Engine() {
		textures = new TextureManager();
		data = new JSONManager();
		shaders = new ShaderManager();
		interp = new PythonInterpreter();
	}
	
	public void load(String filepath) throws FileNotFoundException, IOException, ParseException {
		this.filepath = filepath;
		
		config = new JSON(filepath + "/config.json");
		windowConf = config.getJSON("window");
		gameName = config.getStr("name");
		configureWindow();
		
		interp.execfile(filepath + "/scripts/main.py");
		
		textures.clear();
		data.clear();
		shaders.clear();
		textures.load(filepath);
		data.load(filepath);
		shaders.load(filepath);
	}
	
	public void init() {
		WindowManager.init(640, 480);
		Renderer.init(20, 15);
		QuadRenderer.init();
		ctx = new Context(this);
	}
	
	public void start() {
		interp.set("ctx", ctx);
		interp.get("start").__call__();
	}
	
	public void update() {
		double time = WindowManager.getTime();
		float deltaTime = (float) (time - prevTime);
		
		WindowManager.startFrame();
		Renderer.startFrame();
		
		if(deltaTime < 0.1) {
			interp.set("ctx", ctx);
			interp.get("update").__call__(new PyFloat(deltaTime));
		}
					
		Renderer.endFrame();
		WindowManager.endFrame();
		prevTime = time;
	}
	
	public void stop() {
		WindowManager.close();
	}
	
	private void configureWindow() {
		WindowManager.setTitle(gameName);
		Renderer.setDimensions((float) windowConf.getDouble("width"), (float) windowConf.getDouble("height"));
	}

}
