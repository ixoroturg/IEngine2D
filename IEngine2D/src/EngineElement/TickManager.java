package EngineElement;

import EngineElement.interfaces.*;

public class TickManager implements Tickable{
	protected World world;
	public TickManager setWorld(World world) {
		this.world = world;
		return this;
	}
	@Override
	public void onTick() {
		
	}	
}
