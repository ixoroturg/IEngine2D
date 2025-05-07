package iEngine.element;

import iEngine.element.interfaces.World;
import iEngine.element.interfaces.baseInstance.BaseWorld;

public class WorldBuilder {

	private World world = new BaseWorld(0);

	public static WorldBuilder newBuilder(int tickrate) {
		return new WorldBuilder(tickrate);
	}
	private WorldBuilder(int tickrate) {
		world.setTickrate(tickrate);
	}
	public WorldBuilder create(GameObject gameObject) {
		world.initialize(gameObject);
		return this;
	}
	public World build() {
//		world.setTickrate();
		return world;
	}

}
