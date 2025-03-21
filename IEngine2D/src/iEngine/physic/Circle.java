package iEngine.physic;

import iEngine.math.Point;

public class Circle extends AbstractHitbox<Float>{
	
	public Circle(Point position, float radius) {
		super(position,0);
		this.radius = radius;
	}
	@Override
	public Float getForm() {
		return radius;
	}

	@Override
	public void setForm(Float radius) {
		this.radius = radius;
	}

	@Override
	public boolean isCollised(Hitbox<?> h) {
		return switch(h) {
			case Circle c -> {yield position.getDistance(c.getPosition()) < c.getForm() + radius;}
			case Polygon p -> {
				if(p.getRadius() + radius < position.getDistance(p.getPosition()))
					yield false;
				
				yield true;
			}
		
			default -> false;
		};
	}

	@Override
	public boolean isInside(Point p) {
		return p.getDistance(position) < radius;
	}
	
}
