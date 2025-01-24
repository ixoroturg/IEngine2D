package EngineElement;
import EngineElement.interfaces.Tickable;
import EngineElement.interfaces.World;
import EngineOutput.camera.*;
public abstract class GameObject{
	protected World world = null;
	protected GameObject setWorld(World world) {
		this.world = world;
		if(this instanceof Renderable canRender) {
			world.getStorage().addToRenderList(canRender);
		}
		if(this instanceof Tickable canTick) {
			world.getStorage().addTickable(canTick);
		}
		return this;
	}
}
