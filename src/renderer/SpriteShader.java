package renderer;

public class SpriteShader extends ShaderProgram {

	public SpriteShader() {
		super("res/shaders/sprite/vert.txt", "res/shaders/sprite/frag.txt");
		bindAttribute(0, "pos");
		bindAttribute(1, "uv");
		start();
		setInt("mainTex", 0);
		stop();
	}
	
}
