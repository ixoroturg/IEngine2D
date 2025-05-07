package iEngine.element.interfaces;

import java.util.List;

import iEngine.element.Collider;
import iEngine.element.GameObject;
import iEngine.graphic.Renderable2D;

public interface Storage {

	public List<Renderable2D> getRenderList();

	public void setRenderList(List<Renderable2D> renderList);

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
