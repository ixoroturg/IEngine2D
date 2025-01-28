package EngineElement;

import EngineElement.interfaces.baseInstance.BaseHitbox;
import EngineElement.material.Material;
import EngineMath.Function;
import EngineMath.Point;
import EngineMath.Vector;

public abstract class PhysicHitbox extends BaseHitbox{
	protected float weight;
	protected Material material;
	protected Vector movement = new Vector(0,0);
	/**
	 * forceMoment > 0 - против часовой стрелки
	 */
	protected float forceMoment = 0;
	public PhysicHitbox(Point[] vertex, Point position, double angle, Material material) {
		super(vertex, position, angle);
		this.material = material;
	}
	public void Collise(PhysicHitbox h) {
		if(radius + h.getRadius() > h.getPosition().getDistance(position))
			return;
		
		Point[] p1 = getVertex();
		Point[] p2 = h.getVertex();
		Point collisionPoint = null;
		Point buffer = null;
		float radius = 0;
		int index = 0;
		Vector movement = this.movement.clone().add(h.movement);
		for(int i = 0; i < p1.length; i++) {
			if(!h.isInside(p1[i])) 
				continue;
			for(int j = 0; j < p2.length - 1; j++) {
				if((buffer = 
						Function.getLineSegmentCrossPoint(p1[i], 
						movement.getPoint(p1[i]), 
						p2[j], 
						p2[j + 1]))  != null) {
					if(buffer.getDistance(p1[i]) > radius) {
						radius = buffer.getDistance(p1[i]);
						collisionPoint = buffer;
						index = i;
					}
						
				}
			}
			//Повтор, чтобы не делать лишние % в цикле
			if((buffer = 
					Function.getLineSegmentCrossPoint(p1[i], 
					movement.getPoint(p1[i]), 
					p2[p2.length - 1], 
					p2[0]))  != null) {
				if(buffer.getDistance(p1[i]) > radius) {
					radius = buffer.getDistance(p1[i]);
					collisionPoint = buffer;
				}				
			}	
		}
		//СollisionPoint = Самая глубокая точка пересечения
		collisionPoint.add(p1[index].getVector(collisionPoint));
		Vector force = collisionPoint.getVector(h.getPosition());
		float strength = force.mulScalar(movement);
		//force = new Vector(movement.getAngle()+Math.PI, strength);
		this.movement.add(new Vector(movement.getAngle()+Math.PI, strength));
		forceMoment += force.mulVector(movement);
	}
	
	public void movePhysic() {
		move(movement);
		rotate(forceMoment);
	}
}
