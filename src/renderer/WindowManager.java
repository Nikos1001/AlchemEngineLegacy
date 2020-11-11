package renderer;

import org.lwjgl.glfw.GLFW;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class WindowManager {
	
	private static long window;
	private static int[] width, height;
	
	public static void init(int w, int h) {
		
		if(!GLFW.glfwInit()) {
			System.err.println("Failed to initialize opengl");
			System.exit(-1);
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		window = GLFW.glfwCreateWindow(w, h, "My window", 0, 0);
		
		if(window == 0) {
			System.err.println("Failed to create window");
			System.exit(-1);
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2);
		
		GLFW.glfwShowWindow(window);
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		width = new int[1];
		height = new int[1];
	}
	
	public static void startFrame() {
		GLFW.glfwGetWindowSize(window, width, height);
	}
	
	public static void endFrame() {
		GLFW.glfwPollEvents();
		GLFW.glfwSwapBuffers(window);
		GLFW.glfwSwapInterval(1);
	}
	
	public static void close() {
		GLFW.glfwTerminate();
	}
	
	public static boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public static double getTime() {
		return GLFW.glfwGetTime();
	}
	
	public static float getWidth() {
		return width[0];
	}
	
	public static float getHeight() {
		return height[0];
	}
	
	public static boolean getKey(int key) {
		return GLFW.glfwGetKey(window, key) == 1;
	}
	
	public static void setTitle(String title) {
		GLFW.glfwSetWindowTitle(window, title);
	}
	
}
