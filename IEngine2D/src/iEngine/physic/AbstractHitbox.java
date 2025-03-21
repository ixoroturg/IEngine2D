package iEngine.physic;

import iEngine.math.Function;
import iEngine.math.Point;
import iEngine.math.Vector;

public abstract class AbstractHitbox<F> implements Hitbox<F>{
	protected Point position;
	protected float angle;
	protected float radius = 0;
	public AbstractHitbox(Point position, float angle) {
		this.position = position;
		this.angle = angle;
	}
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public void setPosition(Point p) {
		position = p;
	}
	@Override
	public void move(Vector v) {
		position.add(v);
	}
	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = (float)angle;
	}

	@Override
	public void rotate(double angle) {
		this.angle += angle;
	}

	@Override
	public float getRadius() {
		return radius;
	}

}
