package iEngine.graphic;
import iEngine.element.animation.Animation;
import iEngine.element.animation.AnimationImage;
import iEngine.math.Matrix2D;
import iEngine.math.Point;

import java.awt.Image;
import java.util.*;
public class Model2D {
	protected float width, height;
	protected Matrix2D matrix;
	protected Image[] sprite;
	protected int currentSprite = 0;
	protected Model2D[] models;
	protected Point position;
	Map<Integer,AnimationImage> animation = new TreeMap<>();
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
	
	public Model2D addAnimation(int id, int tickrate, float duration,Image[] sprites) {
		AnimationImage ani = new AnimationImage();
		ani.setTickrate(tickrate)
			.setFunction(sprites)
			.setFullDuration(duration);
		animation.put(id, ani);
		return this;
	}
	public Model2D animate(int id) {
		animation.get(id)
			.repeat(1)
			.start();
		return this;
	}
	public Model2D animateAndReset(int id) {
		animation.get(id)
			.repeat(1)
			.start(ani -> {
				ani.reset(false);
			});
		return this;
	}
	public Model2D animateCycle(int id) {
		animation.get(id)
			.repeat(0)
			.start();
		return this;
	}
	public Model2D stopAnimateCycle(int id, boolean stayInCurrentFrame) {
		animation.get(id).stop(stayInCurrentFrame);
		return this;
	}
	
	
	
//	public RenderContext getRenderContext() {
//		return new RenderContext(sprite,position,angle,width,height,matrix);
//	}

}
