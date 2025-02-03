package iEngine.element;
import iEngine.element.interfaces.Controlable;
import iEngine.element.interfaces.Tickable;
import iEngine.element.interfaces.World;
import iEngine.output.camera.*;
public abstract class GameObject{
	protected World world = null;
	public GameObject setWorld(World world) {
		this.world = world;
		world.getStorage().getGameObjectList().add(this);
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
