package iEngine.output.camera;

import java.awt.Image;
import java.util.List;
import java.util.Map;

//import iEngine.element.BaseGameObject;
import iEngine.element.GameObject;
import iEngine.element.interfaces.World;
import iEngine.math.Point;
import iEngine.math.Vector;

/**
 * Доступны:<br>
 * float angle - поворот камеры<br>
 * float scale - приближение камеры<br>
 * float width, height - размеры камеры в мире<br>
 * float frameWidth, frameHeight - разрешение камеры на экране<br>
 * List<Renderable> renderList - список объектов для отрисовки (может быть удалено)<br>
 * CameraProperty properties - свойства камеры
 */
public abstract class AbstractCamera extends GameObject implements Camera{
	protected Point position = new Point(0,0);
	protected float angle = 0;
	protected float scale = 1;
	protected float width = 1;
	protected float height = 1;
	protected int frameWidth = 0, frameHeight = 0;
	protected List<Renderable> renderList = null;
	protected CameraProperty properties = new CameraProperty();
//	protected float sideRatio = (float)16.0f / 9.0f;
//	protected float kx = 1;
//	protected float ky = 1;
	public AbstractCamera() {
//		System.out.println("Соотношение сторон: "+sideRatio);
	}
	public AbstractCamera(Point position) {
		this.position = position;
//		System.out.println("Соотношение сторон: "+sideRatio);
	}
	@Override
	public Camera setPosition(Point p) {
		position = p;
		return this;
	}
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public Camera move(Vector v) {
		position.add(v.x, -v.y);
		return this;
	}
	@Override
	public Camera setAngle(double angle) {
		this.angle = (float)angle;
		return this;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public Camera rotate(double angle) {
		angle += (float)angle;
		return this;
	}
	@Override
	public Camera setScale(double scale) {
		this.scale = (float)scale;
		return this;
	}
	@Override
	public float getScale() {
		return scale;
	}
	@Override
	public Camera addScale(double scale) {
		this.scale += (float)scale;
		return this;
	}
	@Override
	public Camera mulScale(double scale) {
		this.scale *= (float)scale;
		return this;
	}
	@Override
	public AbstractCamera setWorld(World world) {
		this.world = world;
		renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public Camera setWorld(World world, boolean synchronizeRenderList) {
		this.world = world;
		if(synchronizeRenderList)
			renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public World getWorld() {
		return world;
	}
	@Override
	public Camera addRenderList(List<Renderable> list) {
		renderList.addAll(list);
		return this;
	}
	@Override
	public Camera setRenderList(List<Renderable> list) {
		renderList = list;
		return this;
	}
	@Override
	public Camera setRenderList(World world) {
		renderList = world.getStorage().getRenderList();
		return this;
	}
	@Override
	public List<Renderable> getRenderList() {
		return renderList;
	}
	@Override
	public CameraProperty getProperties() {
		return properties;
	}
	@Override
	public Camera setResolution(int w, int h) {
		frameWidth = w;
		frameHeight = h;
		return this;
	}
	@Override
	public int[] getResolution() {
		return new int[]{frameWidth, frameHeight};
	}
	@Override 
	public Camera setSize(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}
	@Override 
	public float[] getSize() {
		return new float[] {width, height};
	}
	@Override
	public abstract Image render();
}
