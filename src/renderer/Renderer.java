package renderer;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;

public class Renderer {

	public static Matrix4f proj;
	public static float width, height;
	public static float horizMargin, vertMargin;
	
	public static void init(float w, float h) {
		width = w;
		height = h;
		GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_NEAREST);
		GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_NEAREST);
		GL33.glGenerateMipmap(GL33.GL_TEXTURE_2D);
		proj = new Matrix4f();
		calcProjection();
	}
	
	public static void calcProjection() {
		float expectedWidth = (width / height) * WindowManager.getHeight();
		float expectedHeigth = (height / width) * WindowManager.getWidth();
		horizMargin = 0;
		vertMargin = 0;
		if(WindowManager.getWidth() > expectedWidth) {
			float pixelMargin = (WindowManager.getWidth() - expectedWidth) / 2;
			float pixelsPerUnit = WindowManager.getHeight() / height;
			horizMargin = pixelMargin / pixelsPerUnit;
		}
		if(WindowManager.getHeight() > expectedHeigth) {
			float pixelMargin = (WindowManager.getHeight() - expectedHeigth) / 2;
			float pixelsPerUnit = WindowManager.getWidth() / width;
			vertMargin = pixelMargin / pixelsPerUnit;
		}
		proj.identity();
		proj.ortho(-horizMargin, width + horizMargin, height + vertMargin, -vertMargin, -1, 1);
	}
	
	public static void startFrame() {
		calcProjection();
	}
	
	public static void endFrame() {
		
	}
	
	public static void setDimensions(float w, float h) {
		width = w;
		height = h;
		calcProjection();
	}
	
	public static void clear(float r, float g, float b) {
		GL33.glClearColor(r, g, b, 1);
		GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
	}
	
}
