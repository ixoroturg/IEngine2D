package EngineElement;
import EngineMath.*;
public class BaseHitbox implements Hitbox{
	protected Point position;
	protected float angle;
	private Point[] vertexBuffer;
	protected Point[] vertex;
	public BaseHitbox(Point[] vertex, Point position, double angle) {
		this.vertex = vertex;
		this.position = position;
		this.angle = (float)angle;
		vertexBuffer = vertex.clone();
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
	public Point[] getVertex() {
		for(int i = 0; i < vertex.length; i++) {
			vertexBuffer[i].set(vertex[i]).rotate(angle).add(position);
		}
		return vertexBuffer;
	}

	@Override
	public void setVertex(Point[] p) {
		vertex = p;
	}

	@Override
	public boolean isCollisied(Hitbox h) {
		Point[] p1 = getVertex();
		Point[] p2 = h.getVertex();
		for(int i = 0; i < p1.length; i++) {
			for(int j = 0; j < p2.length; j++) {
				if(Function.isLineSegmentCross(p1[i], p1[(i+1) % p1.length], p2[j], p2[(j+1) % p2.length]))
					return true;
			}
		}
		return isInside(h.getPosition());
	}

	@Override
	public boolean isInside(Point p) {
		int count = 0;
		Point[] ps = getVertex();
		Point p2 = new Point(p.x, Float.MAX_VALUE/2);
		for(int i = 0; i < ps.length; i++) {
			if(Function.isLineSegmentCross(p, p2 , ps[i], ps[(i+1) % ps.length]))
				count++;
		}
		return count % 2 != 0;
	}

}