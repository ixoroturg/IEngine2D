package iEngine.physic;
import iEngine.math.*;
/**
 * Интерфейс для хитбоксов
 */
public interface Hitbox<F> {
	/**
	 * @return точка центра хитбокса
	 */
	public Point getPosition();
	/**
	 * Устанавливает точку центра хитбокса
	 * @param p
	 */
	public void setPosition(Point p);
	/**
	 * Перемещает хитбокс
	 * @param v
	 */
	public void move(Vector v);
	/**
	 * @return угол поворота хитбокс
	 */
	public float getAngle();
	/**
	 * Устанавливает угол поворота хитбокса
	 * @param angle
	 */
	public void setAngle(double angle);
	/**
	 * Вращает хитбокс вокруг его центра
	 * @param angle - угол поворота, положительное направление против часовой стрелки
	 */
	public void rotate(double angle);
	/**
	 * @return массив вершин хитбокса в мировом пространстве
	 */
	public F getForm();
	/**
	 * Устанавливает новые вершины<br>
	 * Отсчёт идёт от начала координат<br>
	 * При вызове getVertex() они будут повёрнуты и перемещены к центру хитбокса
	 * @param p
	 */
	public void setForm(F form);
	public float getRadius();
	/**
	 * 
	 * @param h
	 * @return true если переданный хитбокс пересекает границу этого, или находится внутри
	 */
	public boolean isCollised(Hitbox<?> h);
	/**
	 * 
	 * @param p
	 * @return true, если точка внутри хитбокса
	 */
	public boolean isInside(Point p);
	
	
}
