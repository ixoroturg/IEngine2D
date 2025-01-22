package EngineMath;

public class Point{
	public float x;
	public float y;
	public Point(float x,float y) {
		this.x = x;
		this.y = y;
	}
	public Point set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	public Point set(Point p) {
		x = p.x;
		y = p.y;
		return this;
	}
	public Point add(Point p) {
		x += p.x;
		y += p.y;
		return this;
	}
	public Point add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}
	public Point sub(Point p) {
		x -= p.x;
		y -= p.y;
		return this;
	}
	public Point getCenter(Point p) {
		return new Point((x + p.x)/2,(y + p.y)/2);
	}
	public float getDistance(Point p) {
		return (float)Math.sqrt(Math.pow(x-p.x,2) + Math.pow(y - p.y, 2));
	}
	public float getAngle(Point p) {
		double angle = Math.atan( (p.y - y) / (p.x - x) );
		if(p.x < x)
			angle += Math.PI;
		if(angle < 0)
			angle += Math.PI*2;
		return (float)angle;
	}
	public Point rotate(double angle) {
		return rotate(angle, new Point(0,0));
	}
	public Point rotate(double angle, Point center) {
		sub(center);
		float tmp = x;
		x = (float)(Math.cos(angle)*x - Math.sin(angle)*y);
		y = (float)(Math.sin(angle)*tmp + Math.cos(angle)*y );
		add(center);
		return this;
	}
	public Vector getVector(Point p) {
		return new Vector(p.x - x, p.y - y);
	}
	
	public Point clone() {
		return new Point(x,y);
	}
	public boolean equals(Point p) {
		return x == p.x && y == p.y;
	}
	public String toString() {
		return "Точка "+hashCode()+":\n"+
				"\tx: "+ x +"\n"+
				"\ty: "+ y + "\n";
	}
}
