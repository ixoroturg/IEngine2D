package iEngine.physic;
import iEngine.element.GameObject;
import iEngine.math.*;
/**
 * Реализует базовые возможности хитбокса<br><br>
 * Доступно:<br><br>
 * Point position - центр хитбокса<br>
 * float angle - угол поворота хитбокса<br>
 * Point[] vertex - массив вершин хитбокса (относительно (0;0)).
 * Для получения актуальных вершин вызывайте getVertex() - возвращает Point[]<br>
 * float radius - максимальный радиус хитбокса<br>
 */
public class Polygon extends AbstractHitbox<Point[]>{
	private Point[] vertexBuffer;
	protected Point[] vertex;
	public Polygon(Point[] vertex, Point position, float angle) {
		super(position,angle);
		this.vertex = vertex;
		this.position = position;
		this.angle = (float)angle;
		vertexBuffer = new Point[vertex.length];
		for(int i = 0; i < vertex.length; i++) {
			if(this.position.getDistance(vertex[i]) > radius)
				radius = (float) Math.hypot(vertex[i].x, vertex[i].y);
			vertexBuffer[i] = vertex[i].clone();
		}
	}

	@Override
	public Point[] getForm() {
		for(int i = 0; i < vertex.length; i++) {
			vertexBuffer[i].set(vertex[i]).rotate(angle).add(position);
		}
		return vertexBuffer;
	}

	@Override
	public void setForm(Point[] p) {
		vertex = p;
	}
	@Override
	public boolean isCollised(Hitbox h) {
		Point[] p1 = getForm();
		Point[] p2 = (Point[]) h.getForm();
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
		Point[] ps = getForm();
		Point p2 = new Point(p.x, Float.MAX_VALUE/2);
		for(int i = 0; i < ps.length; i++) {
			if(Function.isLineSegmentCross(p, p2 , ps[i], ps[(i+1) % ps.length]))
				count++;
		}
		return count % 2 != 0;
	}

}
