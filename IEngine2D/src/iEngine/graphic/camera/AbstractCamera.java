package iEngine.graphic.camera;

//import java.awt.Image;
import java.util.List;

//import iEngine.element.BaseGameObject;
import iEngine.element.GameObject;
import iEngine.element.interfaces.World;
import iEngine.graphic.Renderable2D;
import iEngine.math.Point;
import iEngine.math.Vector;

/**
 * Доступны:<br>
 * float angle - поворот камеры<br>
 * float scale - приближение камеры<br>
 * float width, height - размеры камеры в мире<br>
 * float frameWidth, frameHeight - разрешение камеры на экране<br>
 * List<Renderable> renderList - список объектов для отрисовки (может быть
 * удалено)<br>
 * CameraProperty properties - свойства камеры
 */
public abstract class AbstractCamera<T> extends GameObject implements Camera<T> {
	protected boolean lockRatio = true;
	protected Point position = new Point(0, 0);
	protected float angle = 0;
	protected float zoom = 1;
	/**
	 * Ширина камеры в мире
	 */
	protected float width = 1;
	/**
	 * Высота камеры в мире
	 */
	protected float height = 1;
	/**
	 * Соотношение сторон камеры
	 */
	protected float ratio = 16.0f/9;
	/**
	 * Разрешение внутреннего холста камеры
	 */
	protected int frameWidth = 0, frameHeight = 0;
	/**
	 * Разрешение камеры на экране
	 */
	protected int nativeWidth = 0, nativeHeight = 0;
	protected List<Renderable2D> renderList = null;
	protected CameraProperty properties = new CameraProperty();

//	protected float sideRatio = (float)16.0f / 9.0f;
//	protected float kx = 1;
//	protected float ky = 1;
	@Override
	public Camera<T> setNativeResolution(int width, int height) {
		nativeWidth = width;
		nativeHeight = height;
		return this;
	}
	@Override
	public int[] getNativeResolution() {
		return new int[]{nativeWidth,nativeHeight};
	}
//	public AbstractCamera() {
////		System.out.println("Соотношение сторон: "+sideRatio);
//	}
//	public AbstractCamera(Point position) {
//		this.position = position;
////		System.out.println("Соотношение сторон: "+sideRatio);
//	}
	@Override
	public Camera<T> setPosition(Point p) {
		position = p;
		return this;
	}
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public Camera<T>move(Vector v) {
		position.add(v.x, -v.y);
		return this;
	}
	@Override
	public Camera<T>setAngle(double angle) {
		this.angle = (float) angle;
		return this;
	}
	@Override
	public float getAngle() {
		return angle;
	}
	@Override
	public Camera<T>rotate(double angle) {
		angle += (float) angle;
		return this;
	}
	@Override
	public Camera<T>setScale(float scale) {
		this.zoom = scale;
		return this;
	}
	@Override
	public float getScale() {
		return zoom;
	}
	@Override
	public Camera<T>addScale(float scale) {
		this.zoom += scale;
		return this;
	}
	@Override
	public Camera<T>mulScale(float scale) {
		this.zoom *= scale;
		return this;
	}
	@Override
	public AbstractCamera<T>setWorld(World world) {
		this.world = world;
		renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public Camera<T>setWorld(World world, boolean synchronizeRenderList) {
		this.world = world;
		if (synchronizeRenderList)
			renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public World getWorld() {
		return world;
	}
	@Override
	public Camera<T> addRenderList(List<Renderable2D> list) {
		renderList.addAll(list);
		return this;
	}
	@Override
	public Camera<T> setRenderList(List<Renderable2D> list) {
		renderList = list;
		return this;
	}
	@Override
	public Camera<T>setRenderList(World world) {
		renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public List<Renderable2D> getRenderList() {
		return renderList;
	}
	@Override
	public CameraProperty getProperties() {
		return properties;
	}
	@Override
	public Camera<T>setResolution(int w, int h) {
		frameWidth = w;
		frameHeight = h;
//		System.out.println("AbstractCamera.setResolution()");
//		System.out.println(w+" "+h);
		return this;
	}
	@Override
	public int[] getResolution() {
		return new int[] { frameWidth, frameHeight
		};
	}
//	@Override
//	public Camera<T>setSize(float width, float height) {
//		this.width = width;
//		this.height = height;
//		return this;
//	}
	@Override
	public float[] getSize() {
		return new float[] { width, height};
	}
	@Override
	public Camera<T>setRatio(float ratio) {
		this.ratio = ratio;
		return this;
	}
	@Override
	public float getRatio() {
		return ratio;
	}
	@Override
	public boolean isRatioLock() {
		return lockRatio;
	}
	@Override
	public Camera<T>setRatioLock(boolean lock) {
		lockRatio = lock;
		return this;
	}
	@Override
	public Camera<T>setWidth(float width) {
		this.width = width;
		height = width / ratio;
		return this;
	}
	@Override
	public Camera<T>setHeight(float height) {
		this.height = height;
		width = height * ratio;
		return this;
	}
}
