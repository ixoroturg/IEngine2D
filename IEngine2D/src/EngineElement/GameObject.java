package EngineElement;
import EngineElement.interfaces.Controlable;
import EngineElement.interfaces.Tickable;
import EngineElement.interfaces.World;
import EngineOutput.camera.*;
public abstract class GameObject{
	protected World world = null;
	public GameObject setWorld(World world) {
		this.world = world;
		if(this instanceof Renderable canRender) {
			world.getStorage().getRenderList().add(canRender);
		}
		if(this instanceof Tickable canTick) {
			world.getStorage().getTickableList().add(canTick);
		}
		if(this instanceof Controlable canControl) {
			world.getStorage().getControlList().add(canControl);
		}
		onCreate();		
		return this;
	}
	protected abstract void onCreate();
}
