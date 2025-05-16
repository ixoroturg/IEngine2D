package iEngine.element;

import iEngine.element.interfaces.World;

public abstract class GameObject {

	protected World world = null;
	public World getWorld() {
		return world;
	}
	public GameObject setWorld(World world) {
		this.world = world;
		return this;
	}
	public abstract void onCreate();

}
