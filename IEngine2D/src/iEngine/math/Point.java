package iEngine.math;
/**
 * Инкапсулирует 2D точку<br>
 * Все методы, возвращающие точку, возвращают this, если не указано иное<br>
 */
public class Point{
	/**
	 * x координата точки
	 */
	public float x;
	/**
	 * y координата точки
	 */
	public float y;
	/**
	 * Создаёт точку с заданными координатами<br>
	 * @param x - x координата точки
	 * @param y - y координата точки
	 */
	public Point(float x,float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Устанавливает координату точки в указанные координаты
	 * @param x - новая x координата
	 * @param y - новая y координата
	 * @return this
	 */
	public Point set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	/**
	 * Копирует координаты из переданной точки
	 * @param p - точка
	 * @return this
	 */
	public Point set(Point p) {
		x = p.x;
		y = p.y;
		return this;
	}
	/**
	 * Складывает координаты переданной точки со своими
	 * @param p - точка
	 * @return this
	 */
	public Point add(Point p) {
		x += p.x;
		y += p.y;
		return this;
	}
	/**
	 * Складывает переданные координаты со своими 
	 * @param x - x координата
	 * @param y - y коррдината
	 * @return this
	 */
	public Point add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	/**
	 * складывает координаты переданного вектора со своими
	 * @param v - вектор
	 * @return this
	 */
	public Point add(Vector v) {
		x += v.x;
		y += v.y;
		return this;
	}
	/**
	 * Вычитает координаты переданной точки из этой
	 * @param p - точка
	 * @return this
	 */
	public Point sub(Point p) {
		x -= p.x;
		y -= p.y;
		return this;
	}
	/**
	 * Возвращает точку, находящуюся между этой точкой и переданной<br>
	 * x = (x1 + x2) / 2;<br>
	 * y = (y1 + y2) / 2;
	 * @param p - точка
	 * @return точка, между этой и переданной 
	 */
	public Point getCenter(Point p) {
		return new Point((x + p.x)/2,(y + p.y)/2);
	}
	/**
	 * Возвращает расстояние до указаной точки
	 * @param p - точка
	 * @return расстояние до точки
	 */
	public float getDistance(Point p) {
		return (float)Math.sqrt(Math.pow(x-p.x,2) + Math.pow(y - p.y, 2));
	}
	/**
	 * Возвращает угол к указанной точки<br>
	 * 0 - горизонталь вправо<br>
	 * Положительное направление против часовой стрелки<br>
	 * угол x в 4 четверти - Math.PI * 3/2 < x < Math.PI * 2 
	 * @param p - точка
	 * @return угол в радианах
	 */
	public float getAngle(Point p) {
		//System.out.println((p.y) +" "+ (p.x - x));
		double angle = Math.atan( (p.y - y) / (p.x - x) );
		if(p.x < x)
			angle += Math.PI;
		if(angle < 0)
			angle += Math.PI*2;
		return (float)angle;
	}
	/**
	 * Аналогичен rotate(angle, new Point(0,0));
	 * @param angle - угол в радианах
	 * @return this
	 */
	public Point rotate(double angle) {
		return rotate(angle, new Point(0,0));
	}
	/**
	 * Поворачивает точку вокруг переданного центра на указанное количество радиан
	 * @param angle - угол в радианах
	 * @param center - точка центра поворота
	 * @return this
	 */
	public Point rotate(double angle, Point center) {
		sub(center);
		float tmp = x;
		x = (float)(Math.cos(angle)*x - Math.sin(angle)*y);
		y = (float)(Math.sin(angle)*tmp + Math.cos(angle)*y );
		add(center);
		return this;
	}
	/**
	 * Возвращает вектор от этой точки до указанной
	 * @param p - точка
	 * @return вектор до точки
	 */
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
