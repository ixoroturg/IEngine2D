package iEngine.graphic;
import iEngine.element.animation.AnimationImage;
import iEngine.math.Matrix2D;
import iEngine.math.Point;
import iEngine.util.Pointer;

import java.awt.Image;
import java.util.*;
public class Model2D {
	protected float width, height;
	protected Matrix2D matrix;
	protected Image[] sprites;
	protected int currentSprite = 0;
	protected Model2D[] models = new Model2D[0];
	protected Pointer<Image> sprite = new Pointer<Image>(null);
	protected Point position;
	protected Map<Integer,AnimationImage> animation = new TreeMap<>();
	protected float angle = 0;
	public Model2D(float width, float height, Point position,float angle,Image sprite) {
		this.width = width;
		this.height = height;
		this.position = position;
		this.angle = angle;
		this.sprite.value = sprite;
	}
	public Point getPosition() {
		return position;
	}
	public Model2D setPosition(Point p) {
		position = p;
		return this;
	}
	public Model2D setInnerModels(Model2D[] models) {
		this.models = models;
		return this;
	}
	public Model2D[] getInnerModels() {
		return models;
	}
	public float getAngle() {
		return angle;
	}
	public Model2D setAngle(float a) {
		angle = a;
		return this;
	}
	public Image getSprite() {
		return sprite.value;
	}
	public Model2D setSprite(Image sprite) {
		this.sprite.value = sprite;
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
	
	public Model2D addAnimation(int id, float duration,Image... sprites) {
		AnimationImage ani = new AnimationImage();
		ani
			.setTarget(sprite)
			.setTickrate((int) (sprites.length / duration))
			.setFunction(sprites)
			.setFullDuration(duration);
		animation.put(id, ani);
		return this;
	}
	public Model2D animate(int id) {
		animation.get(id)
			.repeat(1)
			.onEnd(ani -> {
				ani.stop(true);
			})
			.start();
		return this;
	}
	public Model2D animateAndReset(int id) {
		animation.get(id)
			.repeat(1)
			.onEnd(ani -> {
				ani.reset(false);
				ani.stop(false);
			})
			.start();
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
}
