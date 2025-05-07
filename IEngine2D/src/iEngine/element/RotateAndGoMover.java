package iEngine.element;

import iEngine.element.interfaces.Moveable;
import iEngine.math.Vector;

public class RotateAndGoMover extends PathMover{
	
	public RotateAndGoMover(Moveable target) {
		this.target = target;
	}
	@Override
	public void onTick() {
		if(!active)
			return;
		float angle = target.getAngle();
		double angleToPoint = target.getPosition().getAngle(currentPoint);
		double da = angleToPoint - angle;
		if(da < 0)
			da += Math.PI * 2;
		if(da > Math.PI) {
			da = da - Math.PI * 2;
		}
		
		if(Math.abs(da) < rotationSpeed) {
			target.setAngle(angleToPoint);
		} else {
			rotationSpeed += rotationAcceleration;
			if(rotationSpeed > maxRotationSpeed)
				rotationSpeed = maxRotationSpeed;
			angle = (float) (angle + Math.signum(da) * rotationSpeed);
			target.setAngle(angle);
			return;
		}
		velocity.mul(friction);
		velocity.add(new Vector(angleToPoint,acceleration));
		if(velocity.getLength() > maxVelocity)
			velocity = velocity.getUnitVector().mul(maxVelocity);
		float distance = target.getPosition().getDistance(currentPoint);
		if(velocity.getLength() > distance) {
			target.setPosition(currentPoint.copy());
			if(!nextPoint())
				storage.getTickableList().remove(this);
			return;
		}
		target.move(velocity);
	}
}
