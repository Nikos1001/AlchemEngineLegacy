package renderer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;

public class MeshLoader {

	public static FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public static IntBuffer createIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public static void storeData(int attribute, int dimensions, float[] data) {
		int vbo = GL33.glGenBuffers();
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = createFloatBuffer(data);
		GL33.glBufferData(GL33.GL_ARRAY_BUFFER, buffer, GL33.GL_STATIC_DRAW);
		GL33.glVertexAttribPointer(attribute, dimensions, GL33.GL_FLOAT, false, 0, 0);
		GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
	}
	
	public static int createVAO() {
		int vao = GL33.glGenVertexArrays();
		GL33.glBindVertexArray(vao);
		return vao;
	}
	
	public static void unbindVAO() {
		GL33.glBindVertexArray(0);
	}
	
}
