package iEngine.element;
import java.util.Deque;
import java.util.Iterator;

import iEngine.element.interfaces.Moveable;
import iEngine.element.interfaces.Storage;
import iEngine.element.interfaces.Tickable;
import iEngine.math.Point;
import iEngine.math.Vector;

public abstract class PathMover implements Tickable{
	public static float defaultMaxVelocity = 0,
			defaultMaxRotationSpeed = 0,
			defaultAcceleration = 0,
			defaultRotationAcceleration = 0,
			defaultFriction = 1;
	public static Storage defaultStorage = null;
	protected Vector velocity = new Vector(0,0);
	protected Moveable target;
//	java.util.Deque<E>
	protected Deque<Point> path = new java.util.ArrayDeque<>();
	public float maxVelocity = defaultMaxVelocity,
			maxRotationSpeed = defaultMaxRotationSpeed,	
			acceleration = defaultAcceleration,
			rotationAcceleration = defaultRotationAcceleration,
			friction = defaultFriction,
			rotationSpeed = 0;
	protected Iterator<Point> iterator;
	boolean active = false;
	protected Point currentPoint;
	public Storage storage = defaultStorage;
	
	protected boolean nextPoint() {
		rotationSpeed = 0;
		velocity.set(0, 0);
			currentPoint = path.poll();
		if(currentPoint == null) {
			active = false;
			return false;
		}
		return true;
	}
	public PathMover addPathPoint(Point point) {
		Point last = path.peekLast();
		if(last != null) {
			if(last.equals(point))
				return this;
		} else {
			if(currentPoint != null) {
				if(currentPoint.equals(point))
					return this;
			} else if (target.getPosition().equals(point))
				return this;
		}
		path.add(point);
		if(!storage.getTickableList().contains(this)) {
			storage.getTickableList().add(this);
			nextPoint();
			active = true;
		}
		return this;
	}
	public PathMover setFriction(float friction) {
		this.friction = friction;
		return this;
	}
	public float getFriction() {
		return friction;
	}
	public PathMover setAcceleration(float acceleration) {
		this.acceleration = acceleration;
		return this;
	}
	
	public float getAcceleration() {
		return acceleration;
	}
	
	public PathMover setMaxVelocity(float velocity) {
		this.maxVelocity = velocity;
		return this;
	}
	
	public float getMaxVelocity() {
		return maxVelocity;
	}
	public PathMover setMaxRotationSpeed(float rotationSpeed) {
		maxRotationSpeed = rotationSpeed;
		return this;
	}
	
	public float getMaxRotationSpeed() {
		return maxRotationSpeed;
	}
	
	public PathMover setActive(boolean active) {
		this.active = active;
		return this;
	}
	
	public boolean isActive() {
		return active;
	}
	
}
