package iEngine.element.interfaces;

import iEngine.math.Point;
import iEngine.math.Vector;

public interface Moveable extends Copyable<Moveable>{
	/**
	 * @return точку позиции объекта
	 */
	public Point getPosition();

	/**
	 * Устанавливает точку позиции объекта
	 * 
	 * @param p
	 */
	public void setPosition(Point p);

	/**
	 * Перемещает точки позиции
	 * 
	 * @param v
	 */
	public void move(Vector v);

	/**
	 * @return угол поворота объекта
	 */
	public float getAngle();

	/**
	 * Устанавливает угол поворота объекта
	 * 
	 * @param angle
	 */
	public void setAngle(double angle);

	/**
	 * Вращает объекта
	 * 
	 * @param angle - угол поворота, положительное направление против часовой
	 *              стрелки
	 */
	public void rotate(double angle);
}
