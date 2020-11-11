package renderer;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;

public class QuadRenderer {
	
	private static int vao;
	private static Matrix4f trans;

	public static void init() {
		
		float[] verts = {
			-0.5f, 0.5f, 0f,
			-0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f, 0.5f, 0f,
			-0.5f, 0.5f, 0f
		};
		
		float[] uvs = {
			0, 1,
			0, 0,
			1, 0,
			1, 0,
			1, 1,
			0, 1
		};
		
		vao = MeshLoader.createVAO();
		MeshLoader.storeData(0, 3, verts);
		MeshLoader.storeData(1, 2, uvs);
		MeshLoader.unbindVAO();
		
		trans = new Matrix4f();
		
	}
	
	public static void render() {
		GL33.glBindVertexArray(vao);
		GL33.glEnableVertexAttribArray(0);
		GL33.glEnableVertexAttribArray(1);
		GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, 6);
		GL33.glDisableVertexAttribArray(0);
		GL33.glDisableVertexAttribArray(1);
		MeshLoader.unbindVAO();
	}
	
	public static void render(ShaderProgram shader, float x, float y) {
		trans.identity();
		trans.translate(x, y, 0);
		shader.start();
		shader.setMat4("trans", trans.mulLocal(Renderer.proj));
		shader.setFloat("time", (float)WindowManager.getTime());
		render();
		shader.stop();
	}
	
	public static void render(ShaderProgram shader, float x, float y, float w, float h) {
		trans.identity();
		trans.translate(x, y, 0);
		trans.scale(w, h, 1);
		shader.start();
		shader.setFloat("time", (float)WindowManager.getTime());
		shader.setMat4("trans", trans.mulLocal(Renderer.proj));
		render();
		shader.stop();
	}
	
	public static void render(ShaderProgram shader, float x, float y, float w, float h, float angle) {
		trans.identity();
		trans.translate(x, y, 0);
		trans.rotateZ(angle);
		trans.scale(w, h, 1);
		shader.start();
		shader.setFloat("time", (float)WindowManager.getTime());
		shader.setMat4("trans", trans.mulLocal(Renderer.proj));
		render();
		shader.stop();
	}
	
}
