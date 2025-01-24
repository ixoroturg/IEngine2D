package EngineElement.interfaces;

import java.util.*;
import EngineOutput.camera.Renderable;

public interface Storage {
	public List<Renderable> getRenderList();
	public void setRenderList(List<Renderable> renderList);
	
	public List<Hitbox> getHitboxList();
	public Storage setHitboxList(List<Hitbox> hitboxList);
	
	public List<Tickable> getTickableList();
	public Storage setTickableList(List<Tickable> tickableList);
	
	public List<Controlable> getControlList();
	public Storage setControlList(List<Controlable> controlList);
	
}
