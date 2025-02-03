package iEngine.element.interfaces.baseInstance;

import java.util.*;

import iEngine.element.GameObject;
import iEngine.element.interfaces.*;
import iEngine.output.camera.*;

public class BaseStorage implements Storage{
	private List<Renderable> renderList = new LinkedList<Renderable>();
	private List<Hitbox> hitboxList = new LinkedList<Hitbox>();
	private List<Tickable> tickableList = new LinkedList<Tickable>();
	private List<Controlable> controlList = new LinkedList<Controlable>();
	private List<GameObject> gameObjList = new LinkedList<GameObject>();
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
	@Override
	public List<GameObject> getGameObjectList() {
		return gameObjList;
	}
	@Override
	public Storage setGameObjectList(List<GameObject> gameObjectList) {
		 gameObjList = gameObjectList;
		 return this;
	}
	
}
