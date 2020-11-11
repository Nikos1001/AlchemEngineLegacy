package main;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import engine.Engine;
import renderer.WindowManager;

public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		
		Engine engine = new Engine();
		engine.init();
		engine.load("examples/breakout");
		engine.start();
		while(!WindowManager.shouldClose()) {
			engine.update();
		}
		engine.stop();
		
	}
	

}
