package iEngine.element;

import iEngine.element.interfaces.baseInstance.BaseHitbox;
import iEngine.element.material.Material;
import iEngine.math.Function;
import iEngine.math.Point;
import iEngine.math.Vector;

public abstract class Collider extends BaseHitbox{
	protected float weight;
	protected Material material;
	protected Vector movement = new Vector(0,0);
	/**
	 * forceMoment > 0 - против часовой стрелки
	 */
	protected float forceMoment = 0;
	
	public Collider(Point[] vertex, Point position, double angle, Material material) {
		super(vertex, position, angle);
		this.material = material;
	}
	
	public void Collise(Collider h) {
//		System.out.println("radius: "+radius+", "+h.getRadius()+", "+h.getPosition().getDistance(position)+", "+(radius + h.getRadius() > h.getPosition().getDistance(position)));
		if(radius + h.getRadius() < h.getPosition().getDistance(position)) {
//			System.out.println("Далеко");
			return;
		}
//		if(h.getPosition().getDistance(position) == 0)
//			System.out.println(this);
		
		Point[] p1 = getVertex();
		Point[] p2 = h.getVertex();
		
		Point collisionPoint = null;
		Point buffer = null;
		
		float radius = 0;
		int index = 0;
		Vector movement = this.movement.clone().mul(-1).add(h.movement.clone()).mul(1);
		
		for(int i = 0; i < p1.length; i++) {
			if(!h.isInside(p1[i]))
				continue;
//			System.out.println("Попытка");
			for(int j = 0; j < p2.length; j++) {
//				
				
//				System.out.println(movement);
				if((buffer = 
						Function.getLineSegmentCrossPoint(
						p1[i], 
//						p1[i].clone().sub(movement)s, 
						p1[(i+1) % p1.length].clone(),
						p2[j], 
						p2[(j + 1) % p2.length]
						)
					)  != null) {
					
//					System.out.println("Пересечение");
					if(buffer.getDistance(p1[i]) > radius) {
//						System.out.println("Нашёл\n1: "+p1[i]+"2: "+p1[i].clone().sub(movement)+"3: "+p2[j]+"4: "+ p2[(j + 1) % p2.length]);
						radius = buffer.getDistance(p1[i]);
						collisionPoint = buffer;
						index = i;
					}
						
				}
			}
			//Повтор, чтобы не делать лишние % в цикле
//			if((buffer = 
//					Function.getLineSegmentCrossPoint(p1[i], 
//					movement.getPoint(p1[i]), 
//					p2[p2.length - 1], 
//					p2[0]))  != null) {
//				if(buffer.getDistance(p1[i]) > radius) {
//					radius = buffer.getDistance(p1[i]);
//					collisionPoint = buffer;
//				}				
//			}	
			
		}
		
//		System.out.println(Function.getLineSegmentCrossPoint(
//			new Point(3,2), new Point(8,5),
//			new Point(6,6), new Point(7,3)
//		));
		
//		System.out.println("Пересечение: "+collisionPoint);
		
		if(collisionPoint == null)
			return;
//		System.out.println("Пересечение: "+collisionPoint);
		movement.mul(1);
		//СollisionPoint = Самая глубокая точка пересечения
//		collisionPoint.add(p1[index].getVector(collisionPoint));
//		Vector force = h.getPosition().getVector(collisionPoint);
		Vector radiusVector = position.getVector(collisionPoint);
		radiusVector.mul(0.01f);
		
		System.out.println("\nМой: "+this.movement+"его: "+h.movement+"суммарно: "+movement);
		
		
		System.out.println("радиус: "+radiusVector);
		float force = - radiusVector.mulScalar(movement);
		System.out.println("сила: "+force);
		//force = new Vector(movement.getAngle()+Math.PI, strength);
//		this.movement.add(new Vector(movement.getAngle()+Math.PI, strength));
		forceMoment += radiusVector.mulVector(movement) / 100;
//		System.out.println("moment: "+forceMoment);
		this.movement.add(movement.getUnitVector().mul(force));
//		this.movement.add((float)Math.cos(movement.getAngle())*force,(float)Math.sin(movement.getAngle())*force);
	}
	
	
	public void moveDone() {
		move(movement);
		rotate(forceMoment);
//		System.out.println(position);
	}
}
