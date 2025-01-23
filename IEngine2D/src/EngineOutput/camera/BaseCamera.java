package EngineOutput.camera;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import EngineElement.World;
import EngineMath.Point;
import EngineMath.Vector;

public abstract class BaseCamera implements Camera{
	protected Point position = new Point(0,0);
	protected float angle = 0;
	protected float scale = 1;
	protected World world = null;
	protected int width = 0, height = 0;
	protected Map<Integer,List<Renderable>> renderMap = null;
	protected CameraProperty properties = new CameraProperty();
	public BaseCamera() {
		
	}
	public BaseCamera(Point position) {
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
		setRenderMap(this.world.renderObjects);
		return this;
	}
	@Override
	public World getWorld() {
		return world;
	}
	@Override
	public Camera addRenderList(int key, List<Renderable> list) {
		renderMap.put(key, list);
		return this;
	}
	@Override
	public Camera setRenderMap(Map<Integer, List<Renderable>> map) {
		renderMap = map;
		return this;
	}
	@Override
	public Map<Integer, List<Renderable>> getRenderLists() {
		return renderMap;
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
	@Override
	public Image render() {
		renderStart();
		renderMap.values().forEach(list -> {
			list.forEach(renderObject -> {
				renderObject(renderObject);
			});
		});
		return renderComplete();
	}
	protected abstract void renderStart();
	protected abstract void renderObject(Renderable renderObject);
	protected abstract Image renderComplete();
	
}
