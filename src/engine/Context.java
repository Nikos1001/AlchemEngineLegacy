package engine;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.lwjgl.glfw.GLFW;
import org.python.jsr223.PyScriptEngineScope.engine_descriptor;

import json.JSON;
import renderer.QuadRenderer;
import renderer.Renderer;
import renderer.ShaderProgram;
import renderer.Texture;
import renderer.WindowManager;

public class Context {
	
	private Engine engine;
	
	public Context(Engine engine) {
		this.engine = engine;
	}
	
	public void spr(Texture t, float x, float y, float w, float h, float r, ShaderProgram shader) {
		t.bind();
		QuadRenderer.render(shader, x + Math.abs(w) / 2, y + Math.abs(h) / 2, w, h, r);
		t.unbind();
	}
	
	public void spr(Texture t, float x, float y, float w, float h, float r) {
		spr(t, x, y, w, h, r, loadShader("-spriteDefault"));
	}
	
	public void spr(Texture t, float x, float y, float w, float h) {
		spr(t, x, y, w, h, 0, loadShader("-spriteDefault"));
	}
	
	public void spr(Texture t, float x, float y) {
		spr(t, x, y, 1, 1, 0, loadShader("-spriteDefault"));
	}
	
	public void clr(float r, float g, float b) {
		Renderer.clear(r, g, b);
	}
	
	public float width() {
		return Renderer.width;
	}
	
	public float height() {
		return Renderer.height;
	}
	
	public boolean key(String key) {
		if(key.length() == 1) {
			return WindowManager.getKey(key.charAt(0));
		}
		return false;
	}
	
	public Texture loadSprite(String path) {
		return engine.textures.get(path);
	}
	
	public JSON loadJSON(String path) throws FileNotFoundException, IOException, ParseException {
		return engine.data.get(path);
	}
	
	public Tilemap loadTilemap(String path) throws FileNotFoundException, IOException, ParseException {
		Tilemap map = new Tilemap();
		map.load(loadJSON(path));
		return map;
	}
	
	public ShaderProgram loadShader(String path) {
		ShaderProgram shad = engine.shaders.get(path);
		return shad;
	}
	
	public void drawTilemap(Tilemap map, float x, float y, float tileW, float tileH, int tileStartX, int tileStartY, int tileRegionWidth, int tileRegionHeight) {
		for(int i = tileStartX; i < tileStartX + tileRegionWidth; i ++) {
			for(int j = tileStartY; j < tileStartY + tileRegionHeight; j ++) {
				
				int tileId = map.getTile(i, j);
				Tile tile = map.tiles.get(tileId);
				
				if(tile.render) {
					Texture tex = loadSprite(tile.sprite);
					
					float renderX = x + (i) * tileW;
					float renderY = y + (j) * tileH;
					
					spr(tex, renderX, renderY, 1, 1);
				}
				
			}
		}
	}
	
}
