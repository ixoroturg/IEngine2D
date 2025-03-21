package iEngine.render;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import iEngine.element.*;
//import iEngine.element.interfaces.GameObject;
import iEngine.element.interfaces.World;
import iEngine.math.*;
/**
 * Интерфейс для камеры
 */
public interface Camera {

	public Camera setSize(float width, float height);
	public float[] getSize();
	/**
	 * Устанавливает позицию камеры в мире
	 * @param p
	 * @return this
	 */
	public Camera setPosition(Point p);
	/**
	 * Получает текущую позицию камеры
	 * @return
	 */
	public Point getPosition();
	/**
	 * Перемещает камеру на заданный вектор
	 * @param v
	 * @return this
	 */
	public Camera move(Vector v);
	/**
	 * Устанавливает угол поворота камеры
	 * @param angle
	 * @return this
	 */
	public Camera setAngle(double angle);
	/**
	 * 
	 * @return угол поворота камеры
	 */
	public float getAngle();
	/**
	 * Поворачивает камеру на указанный угол<br>
	 * angle > 0: против часовой стрелки
	 * @param angle
	 * @return
	 */
	public Camera rotate(double angle);
	/**
	 * Устанавливает степень масштабирования камеры<br>
	 * Может зависеть от разрешения экрана (1 для FHD, 4 для 4k)
	 * @param scale
	 * @return
	 */
	public Camera setScale(double scale);
	/**
	 * 
	 * @return текущую степень масштабирования
	 */
	public float getScale();
	/**
	 * Увеличивает текущее масштабирование
	 * @param scale
	 * @return
	 */
	public Camera addScale(double scale);
	/**
	 * Умножает текущее масштабирование
	 * @param scale
	 * @return
	 */
	public Camera mulScale(double scale);
	/**
	 * Устанавливает разрешение камеры
	 * @param width
	 * @param height
	 * @return
	 */
	public Camera setResolution(int width, int height);
	/**
	 * 
	 * @return разрешение камеры, [0] = width, [1] = height
	 */
	public int[] getResolution();
	/**
	 * Устанавливает мир для текущей камеры<br>
	 * @param world
	 * @return this
	 */
	public Camera setWorld(World world);
	/**
	 * Устанавливает мир для текущей камеры<br>
	 * если synchronizeRenderList устанавливает списки объектов для рендеринга на списки из нового мира
	 * @param world
	 * @return this
	 */
	public Camera setWorld(World world, boolean synchronizeRenderList);
	/**
	 * 
	 * @return текущий мир камеры
	 */
	public World getWorld();
	/**
	 * Добавляет список объектов для рендеринга с указанной группой
	 * @param key
	 * @param list
	 * @return
	 */
	public Camera addRenderList(List<Renderable> list);
	/**
	 * Добавляет список объектов для рендеринга с указанной группой
	 * @param key
	 * @param list
	 * @return
	 */
//	public Camera removeRenderList(List<Renderable> list);
	/**
	 * Устанавливает все списки объектов для рендеринга
	 * @param map
	 * @return
	 */
	public Camera setRenderList(List<Renderable> list);
	/**
	 * Устанавливает список объектов для рендеринга из переданного мира
	 * @return this
	 */
	public Camera setRenderList(World world);
	/**
	 * Возвращает списки объектов для рендеринга
	 * @return this
	 */
	public List<Renderable> getRenderList();
	
	/**
	 * @return особые настройки камеры
	 */
	public CameraProperty getProperties();
	/**
	 * @return Image, который был создан камерой
	 */
	public Image render();
}
