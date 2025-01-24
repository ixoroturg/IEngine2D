package EngineElement.interfaces;

import java.util.*;
import EngineOutput.camera.Renderable;

public interface Storage {
	public List<Renderable> getRenderList();
	public void setRenderList(List<Renderable> renderList);
	public void addToRenderList(Renderable renderObject);
	public void removeFromRenderList(Renderable renderObject);
	
	public List<Hitbox> getHitboxList();
	public void addHitbox(Hitbox h);
	public void removeHitbox(Hitbox h);
	
	public List<Tickable> getTickableList();
	public void addTickable(Tickable t);
	public void removeTickable(Tickable t);
}
