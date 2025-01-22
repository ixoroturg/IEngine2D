package EngineElement;
import EngineMath.*;
public interface Hitbox {
	
	public Point getPosition();
	public void setPosition(Point p);
	public void move(Vector v);
	
	public float getAngle();
	public void setAngle(double angle);
	public void rotate(double angle);
	
	public Point[] getVertex();
	public void setVertex(Point[] p);
	
	public boolean isCollisied(Hitbox h);
	public boolean isInside(Point p);
	
	
}
