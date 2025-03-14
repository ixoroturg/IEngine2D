package iEngine.element.interfaces;

import java.util.*;

import iEngine.element.Collider;
import iEngine.element.GameObject;
//import iEngine.element.GameObject;
import iEngine.output.camera.Renderable;

public interface Storage {
	public List<Renderable> getRenderList();
	public void setRenderList(List<Renderable> renderList);
	
	public List<Hitbox> getHitboxList();
	public Storage setHitboxList(List<Hitbox> hitboxList);
	
	public List<Tickable> getTickableList();
	public Storage setTickableList(List<Tickable> tickableList);
	
	public List<Controlable> getControlList();
	public Storage setControlList(List<Controlable> controlList);
	
	public List<GameObject> getGameObjectList();
	public Storage setGameObjectList(List<GameObject> gameObjectList);
	public List<Collider> getColliderList();
	public Storage setColliderList(List<Collider> colliderList);
}
