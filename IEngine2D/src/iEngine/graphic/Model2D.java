package iEngine.graphic;
import iEngine.math.Matrix2D;
import iEngine.math.MatrixOld;
import iEngine.math.Point;

import java.awt.Image;
public class Model2D {
	protected float width, height;
	protected Matrix2D matrix;
	protected Image[] sprite;
	protected int currentSprite = 0;
	protected Model2D[] models;
	protected Point position;
	
	protected float angle = 0;
	public Model2D(float width, float height, Point position,float angle,Image... sprites) {
		this.width = width;
		this.height = height;
		this.position = position;
		this.angle = angle;
		sprite = sprites;
	}
	public Point getPosition() {
		return position;
	}
	public Model2D setPosition(Point p) {
		position = p;
		return this;
	}
	public float getAngle() {
		return angle;
	}
	public Model2D setAngle(float a) {
		angle = a;
		return this;
	}
	public Image getSprite() {
		return sprite[currentSprite];
	}
	public Model2D setSprite(Image[] sprites) {
		sprite = sprites;
		return this;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public Matrix2D getMatrix() {
		return matrix;
	}
	
	
	
	
//	public RenderContext getRenderContext() {
//		return new RenderContext(sprite,position,angle,width,height,matrix);
//	}

}
