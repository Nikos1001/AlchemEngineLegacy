package renderer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;


public class ShaderProgram {
	
	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	public ShaderProgram(String vertexFile, String fragmentFile){
		vertexShaderID = loadShader(vertexFile,GL33.GL_VERTEX_SHADER);
		fragmentShaderID = loadShader(fragmentFile,GL33.GL_FRAGMENT_SHADER);
		programID = GL33.glCreateProgram();
		GL33.glAttachShader(programID, vertexShaderID);
		GL33.glAttachShader(programID, fragmentShaderID);
		GL33.glLinkProgram(programID);
		GL33.glValidateProgram(programID);
	}
	
	public void start(){
		GL33.glUseProgram(programID);
	}
	
	public void stop(){
		GL33.glUseProgram(0);
	}
	
	public void cleanUp(){
		stop();
		GL33.glDetachShader(programID, vertexShaderID);
		GL33.glDetachShader(programID, fragmentShaderID);
		GL33.glDeleteShader(vertexShaderID);
		GL33.glDeleteShader(fragmentShaderID);
		GL33.glDeleteProgram(programID);
	}
	
	public void bindAttribute(int attribute, String variableName){
		GL33.glBindAttribLocation(programID, attribute, variableName);
	}
	
	private static int loadShader(String file, int type){
		StringBuilder shaderSource = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("//\n");
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = GL33.glCreateShader(type);
		GL33.glShaderSource(shaderID, shaderSource);
		GL33.glCompileShader(shaderID);
		if(GL33.glGetShaderi(shaderID, GL33.GL_COMPILE_STATUS) == GL33.GL_FALSE){
			System.out.println(GL33.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
	}
	
	public void setInt(String varName, int val) {
		int loc = GL33.glGetUniformLocation(programID, varName);
		GL33.glUniform1i(loc, val);
	}

	
	public void setMat4(String varName, Matrix4f matrix) {
		int loc = GL33.glGetUniformLocation(programID, varName);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		matrix.get(buffer);
		GL33.glUniformMatrix4fv(loc, false, buffer);
	}
	
	public void setFloat(String varName, float val) {
		int loc = GL33.glGetUniformLocation(programID, varName);
		GL33.glUniform1f(loc, val);
	}

}
