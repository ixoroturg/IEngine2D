package iEngine.element;
import iEngine.element.interfaces.*;
import iEngine.element.interfaces.baseInstance.BaseWorld;
public class WorldBuilder {
	private World world= new BaseWorld();
	public static WorldBuilder newBuilder() {
		return new WorldBuilder();
	}
	private WorldBuilder() {}
	
	public WorldBuilder create(GameObject gameObject) {
		gameObject.initialize(world);
		return this;
	}
	public World build(int tickrate) {
		world.setTickrate(tickrate);
		return world;
	}
	
}
