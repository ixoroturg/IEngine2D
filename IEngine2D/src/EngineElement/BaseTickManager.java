package EngineElement;

import EngineElement.interfaces.*;

public class BaseTickManager implements Tickable{
	protected World world;
	public BaseTickManager setWorld(World world) {
		this.world = world;
		return this;
	}
	@Override
	public void onTick() {
		world.getStorage().getTickableList().forEach(tick -> {
			tick.onTick();
		});
	}	
}
