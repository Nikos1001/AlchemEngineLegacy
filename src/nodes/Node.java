package nodes;

import java.util.ArrayList;

public class Node {
	
	private ArrayList<Node> children;
	
	Node() {
		children = new ArrayList<Node>();
	}
	
	public void start() {
		for(Node n : children) n.start();
	}
	
	public void update(float dt) {
		for(Node n : children) n.update(dt);
	}
	
	public void render() {
		for(Node n : children) n.render();
	}
	
	public void addChild(Node n) {
		children.add(n);
	}
	
}
