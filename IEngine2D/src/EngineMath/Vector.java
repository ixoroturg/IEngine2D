package EngineMath;

public class Vector {
	public float x;
	public float y;
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public Vector(double angle, float length) {
		x = (float) (Math.cos(angle) * length);
		y = (float) (Math.sin(angle) * length);
	}
	public Vector set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}
	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		return this;
	}
	public float getLength() {
		return (float)Math.sqrt(x*x + y*y);
	}
	public float getAngle() {
		double angle = Math.atan(y / x);
		if(x < 0)
			angle += Math.PI;
		if(angle < 0)
			angle += Math.PI*2;
		return (float)angle;
	}
	public Point getPoint(Point start) {
		return new Point(start.x+x , start.y+y);
	}
	public Vector rotate(double angle) {
		float tmp = x;
		x = (float) (Math.cos(angle)*x - Math.sin(angle)*y);
		y = (float) (Math.cos(angle)*y + Math.sin(angle)*tmp);
		return this;
	}
	public float mulScalar(Vector v) {
		return v.x * x + v.y * y;
	}
	public float mulVector(Vector v) {
		return x*v.y - y*v.x;
	}
	public Vector clone() {
		return new Vector(x,y);
	}
	public boolean equals(Vector v) {
		return v.x == x && v.y == y;
	}
	public String toString() {
		return "Вектор "+hashCode()+":\n"+
				"\tx: " + x + "\n"+
				"\ty: " + y + "\n";
	}
}
