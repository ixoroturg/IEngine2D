package EngineElement.interfaces.baseInstance;

import java.util.*;

import EngineElement.interfaces.Hitbox;
import EngineElement.interfaces.Storage;
import EngineElement.interfaces.Tickable;
import EngineOutput.camera.Renderable;

public class BaseStorage implements Storage{
	private List<Renderable> renderList = new LinkedList<Renderable>();
	private List<Hitbox> hitboxList = new LinkedList<Hitbox>();
	private List<Tickable> tickableList = new LinkedList<Tickable>();
	@Override
	public List<Renderable> getRenderList() {
		return renderList;
	}
	@Override
	public void setRenderList(List<Renderable> renderList) {
		this.renderList = renderList;
	}

	@Override
	public void addToRenderList(Renderable renderObject) {
		renderList.add(renderObject);
	}

	@Override
	public void removeFromRenderList(Renderable renderObject) {
		renderList.remove(renderObject);
	}

	@Override
	public List<Hitbox> getHitboxList() {
		return hitboxList;
	}

	@Override
	public void addHitbox(Hitbox h) {
		hitboxList.add(h);
	}

	@Override
	public void removeHitbox(Hitbox h) {
		hitboxList.remove(h);
	}
	@Override
	public List<Tickable> getTickableList() {
		return tickableList;
	}
	@Override
	public void addTickable(Tickable t) {
		tickableList.add(t);
	}
	@Override
	public void removeTickable(Tickable t) {
		tickableList.remove(t);
	}
	
}
