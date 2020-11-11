package engine;

public class CollisionBox {
	
	public float x, y, w, h;
	
	public CollisionBox(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean collidingWith(CollisionBox other) {
		if(x > other.x + other.w) return false;
		if(x + w < other.x) return false;
		if(y > other.y + other.h) return false;
		if(y + h < other.y) return false;
		return true;
	}
	
	public Collision collideWith(CollisionBox other) {
		Collision col = new Collision();
		if(collidingWith(other)) {
			col.collided = true;
			float centerX = x + w / 2;
			float centerY = y + h / 2;
			float otherCenterX = other.x + other.w / 2;
			float otherCenterY = other.y + other.h / 2;
			float dx = Math.abs(centerX - otherCenterX) / (w + other.w);
			float dy = Math.abs(centerY - otherCenterY) / (h + other.h);
			
			if(dx > dy) { // Left or right side
				col.horizontal = true;
				if(centerX > otherCenterX) { // Right
					x = other.x + other.w;
					col.side = 3;
				} else { // Left
					x = other.x - w;
					col.side = 2;
				}
			} else { // Top or bottom side
				col.vertical = true;
				if(centerY > otherCenterY) { // Bottom
					y = other.y + other.h;
					col.side = 1;
				} else { // Top
					y = other.y - h;
					col.side = 0;
				}
			}
		}
		return col;
	}
	
}
