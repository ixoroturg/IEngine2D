package iEngine.element;

import iEngine.element.interfaces.Moveable;
import iEngine.math.Vector;

public class MoveToPointMover extends PathMover {
	public MoveToPointMover(Moveable target) {
		this.target = target;
		velocity.setAngle(target.getAngle());
	}
	@Override
	public void onTick() {
		if(!active)
			return;
		double angle = target.getAngle();
		double angleToPoint = target.getPosition().getAngle(currentPoint);
		double da = angleToPoint - angle;
		if(da < 0)
			da += Math.PI * 2;
		if(da > Math.PI) {
			da = da - Math.PI * 2;
		}
		
		if(Math.abs(da) < rotationSpeed) {
			angle = angleToPoint;
//			target.setAngle(angleToPoint);
//			velocity.setAngle(angleToPoint);
		} else {
			rotationSpeed += rotationAcceleration;
			if(rotationSpeed > maxRotationSpeed)
				rotationSpeed = maxRotationSpeed;
			angle = (float) (angle + Math.signum(da) * rotationSpeed);
			
			
		}
		target.setAngle(angle);
//		velocity.setAngle(angle);
		velocity.add(new Vector(angle,acceleration));
		
		if(velocity.getLength() > maxVelocity) {
			velocity = velocity.getUnitVector().mul(maxVelocity);
		}
		
		if(velocity.getLength() > target.getPosition().getDistance(currentPoint)) {
			target.setPosition(currentPoint.copy());
			target.setAngle(velocity.getAngle());
			if(!nextPoint())
				storage.getTickableList().remove(this);
			return;
		}
//		float distance = target.getPosition().getDistance(currentPoint);
//		if(velocity.getLength() < acceleration) {
//			target.setPosition(currentPoint.clone());
//			target.setAngle(velocity.getAngle());
//			if(!nextPoint())
//				storage.getTickableList().remove(this);
//			return;
//		} else 
//		if(distance < acceleration / 2)
//			velocity = velocity.getUnitVector().mul(distance);
//		
		velocity.mul(friction);
		
		target.move(velocity);
	}
}
