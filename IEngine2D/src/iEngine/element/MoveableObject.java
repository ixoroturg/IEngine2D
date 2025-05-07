package iEngine.element;

import iEngine.element.interfaces.Moveable;
import iEngine.math.Point;
import iEngine.math.Vector;

public class MoveableObject implements Moveable{
	protected Point position;
	protected float angle;
	
	public MoveableObject(Point position, float angle) {
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
	public Moveable paste(Moveable t) {
		position = t.getPosition().copy();
		angle = t.getAngle();
		return this;
	}
	@Override
	public Moveable copy() {
		return new MoveableObject(position.copy(),angle);
	}
}
