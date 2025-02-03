package iEngine.output.camera;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import iEngine.element.interfaces.World;
import iEngine.math.Point;
import iEngine.math.Vector;

public abstract class AbstractCamera implements Camera{
	protected Point position = new Point(0,0);
	protected float angle = 0;
	protected float scale = 1;
	protected World world = null;
	protected int width = 0, height = 0;
	protected List<Renderable> renderList = null;
	protected CameraProperty properties = new CameraProperty();
	public AbstractCamera() {
		
	}
	public AbstractCamera(Point position) {
		this.position = position;
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
		position.add(v);
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
	public Camera setWorld(World world) {
		this.world = world;
		//setRenderList(this.world.getStorage().getRenderList());
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
	public List<Renderable> getRenderList() {
		return renderList;
	}
	@Override
	public CameraProperty getProperties() {
		return properties;
	}
	@Override
	public Camera setResolution(int w, int h) {
		width = w;
		height = h;
		return this;
	}
	@Override
	public int[] getResolution() {
		return new int[]{width, height};
	}
	public abstract Image render();
	
}
