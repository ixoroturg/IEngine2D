package iEngine.graphic.camera;

//import java.awt.Image;
import java.util.List;

import iEngine.element.interfaces.World;
import iEngine.graphic.Renderable2D;
import iEngine.math.Point;
import iEngine.math.Vector;

/**
 * Интерфейс для камеры
 */
public interface Camera<T> {
	
//	public Camera<T> setSize(float width, float height);
	public Camera<T> setWidth(float width);
	public Camera<T> setHeight(float height);
	public float[] getSize();
	public Camera<T> setRatioLock(boolean lock);
	public boolean isRatioLock();
	/**
	 * Устанавливает позицию камеры в мире
	 * 
	 * @param p
	 * 
	 * @return this
	 */
	public Camera<T> setPosition(Point p);

	/**
	 * Получает текущую позицию камеры
	 * 
	 * @return
	 */
	public Point getPosition();

	/**
	 * Перемещает камеру на заданный вектор
	 * 
	 * @param v
	 * 
	 * @return this
	 */
	public Camera<T> move(Vector v);

	/**
	 * Устанавливает угол поворота камеры
	 * 
	 * @param angle
	 * 
	 * @return this
	 */
	public Camera<T> setAngle(double angle);

	/**
	 * @return угол поворота камеры
	 */
	public float getAngle();

	/**
	 * Поворачивает камеру на указанный угол<br>
	 * angle > 0: против часовой стрелки
	 * 
	 * @param angle
	 * 
	 * @return
	 */
	public Camera<T> rotate(double angle);

	/**
	 * Устанавливает степень масштабирования камеры<br>
	 * Может зависеть от разрешения экрана (1 для FHD, 4 для 4k)
	 * 
	 * @param scale
	 * 
	 * @return
	 */
	public Camera<T> setScale(float zoom);

	/**
	 * @return текущую степень масштабирования
	 */
	public float getScale();

	/**
	 * Увеличивает текущее масштабирование
	 * 
	 * @param scale
	 * 
	 * @return
	 */
	public Camera<T> addScale(float scale);
//	public Camera<T> setPreferSide(byte side);

	/**
	 * Умножает текущее масштабирование
	 * 
	 * @param scale
	 * 
	 * @return
	 */
	public Camera<T> mulScale(float scale);

	/**
	 * Устанавливает разрешение камеры
	 * 
	 * @param width
	 * @param height
	 * 
	 * @return
	 */
	public Camera<T> setResolution(int width, int height);

	/**
	 * @return разрешение камеры, [0] = width, [1] = height
	 */
	public int[] getResolution();

	/**
	 * Устанавливает мир для текущей камеры<br>
	 * 
	 * @param world
	 * 
	 * @return this
	 */
	public Camera<T> setWorld(World world);

	/**
	 * Устанавливает мир для текущей камеры<br>
	 * если synchronizeRenderList устанавливает списки объектов для рендеринга
	 * на списки из нового мира
	 * 
	 * @param world
	 * 
	 * @return this
	 */
	public Camera<T> setWorld(World world, boolean synchronizeRenderList);

	/**
	 * @return текущий мир камеры
	 */
	public World getWorld();

	/**
	 * Добавляет список объектов для рендеринга с указанной группой
	 * 
	 * @param key
	 * @param list
	 * 
	 * @return
	 */
	public Camera<T> addRenderList(List<Renderable2D> list);

	/**
	 * Добавляет список объектов для рендеринга с указанной группой
	 * 
	 * @param key
	 * @param list
	 * 
	 * @return
	 */
//	public Camera<T> removeRenderList(List<Renderable> list);
	/**
	 * Устанавливает все списки объектов для рендеринга
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public Camera<T> setRenderList(List<Renderable2D> list);

	/**
	 * Устанавливает список объектов для рендеринга из переданного мира
	 * 
	 * @return this
	 */
	public Camera<T> setRenderList(World world);

	/**
	 * Возвращает списки объектов для рендеринга
	 * 
	 * @return this
	 */
	public List<Renderable2D> getRenderList();

	/**
	 * @return особые настройки камеры
	 */
	public CameraProperty getProperties();

	/**
	 * @return Image, который был создан камерой
	 */
	public T render();

	public Camera<T> setRatio(float ratio);
	public float getRatio();
	
}
