package EngineElement;

import EngineElement.interfaces.baseInstance.BaseHitbox;
import EngineMath.Point;

public class PhysicHitbox extends BaseHitbox{
	protected float weight;
	public PhysicHitbox(Point[] vertex, Point position, double angle) {
		super(vertex, position, angle);
	}
	public void Collise(PhysicHitbox h) {
		
	}
}
