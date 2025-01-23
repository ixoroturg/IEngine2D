package EngineMath;

/**
 * Инкапсулирует 2D вектор<br>
 * Все методы, возвращающие Vector, возвращают this, если не указано иное
 */
public class Vector {
	public float x;
	public float y;
	/**
	 * Создаёт вектор с заданными координатами
	 * @param x
	 * @param y
	 */
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Создайт вектор из угла и длины
	 * @param angle
	 * @param length
	 */
	public Vector(double angle, float length) {
		x = (float) (Math.cos(angle) * length);
		y = (float) (Math.sin(angle) * length);
	}
	/**
	 * Устанавливает координаты вектора
	 * @param x
	 * @param y
	 * @return this
	 */
	public Vector set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	/**
	 * Складывает с указанным вектором
	 * @param v
	 * @return this
	 */
	public Vector add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}
	/**
	 * Складывает с указанными координатами
	 * @param x
	 * @param y
	 * @return this
	 */
	public Vector add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	/**
	 * Вычитает указанный вектор 
	 * @param v
	 * @return this
	 */
	public Vector sub(Vector v) {
		x -= v.x;
		y -= v.y;
		return this;
	}
	/**
	 * Вычитает указанные координаты
	 * @param x
	 * @param y
	 * @return this
	 */
	public Vector sub(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	/**
	 * @return длина вектора
	 */
	public float getLength() {
		return (float)Math.sqrt(x*x + y*y);
	}
	/**
	 * 0 - горизонталь вправо<br>
	 * угол X в 4 четверти: Math.PI * 3/2 < X < Math.PI * 2
	 * @return угол вектора
	 */
	public float getAngle() {
		double angle = Math.atan(y / x);
		if(x < 0)
			angle += Math.PI;
		if(angle < 0)
			angle += Math.PI*2;
		return (float)angle;
	}
	/**
	 * @param start - точка начала вектора
	 * @return точка конца вектора
	 */
	public Point getPoint(Point start) {
		return new Point(start.x+x , start.y+y);
	}
	/**
	 * Поворачивает вектор<br>
	 * angle > 0: против часовой стрелки
	 * @param angle - угол
	 * @return this
	 */
	public Vector rotate(double angle) {
		float tmp = x;
		x = (float) (Math.cos(angle)*x - Math.sin(angle)*y);
		y = (float) (Math.cos(angle)*y + Math.sin(angle)*tmp);
		return this;
	}
	/**
	 * 
	 * @param v - вектор
	 * @return скалярное произведение
	 */
	public float mulScalar(Vector v) {
		return v.x * x + v.y * y;
	}
	/**
	 * В 2D мире скалярное произведение равно z координате нового вектора, но так как 3-го измерения нет, возвращается число
	 * @param v - вектор
	 * @return векторное произведение
	 */
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
