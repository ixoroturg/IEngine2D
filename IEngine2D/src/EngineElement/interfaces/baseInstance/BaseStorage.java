package EngineElement.interfaces.baseInstance;

import java.util.*;

import EngineElement.interfaces.Controlable;
import EngineElement.interfaces.Hitbox;
import EngineElement.interfaces.Storage;
import EngineElement.interfaces.Tickable;
import EngineOutput.camera.Renderable;

public class BaseStorage implements Storage{
	private List<Renderable> renderList = new LinkedList<Renderable>();
	private List<Hitbox> hitboxList = new LinkedList<Hitbox>();
	private List<Tickable> tickableList = new LinkedList<Tickable>();
	private List<Controlable> controlList = new LinkedList<Controlable>();
	@Override
	public List<Renderable> getRenderList() {
		return renderList;
	}
	@Override
	public void setRenderList(List<Renderable> renderList) {
		this.renderList = renderList;
	}
	@Override
	public List<Hitbox> getHitboxList() {
		return hitboxList;
	}
	@Override
	public List<Tickable> getTickableList() {
		return tickableList;
	}
	@Override
	public List<Controlable> getControlList() {
		return controlList;
	}
	@Override
	public Storage setHitboxList(List<Hitbox> hitboxList) {
		this.hitboxList = hitboxList;
		return this;
	}
	@Override
	public Storage setTickableList(List<Tickable> tickableList) {
		this.tickableList = tickableList;
		return this;
	}
	@Override
	public Storage setControlList(List<Controlable> controlList) {
		this.controlList = controlList;
		return this;
	}
	
}
