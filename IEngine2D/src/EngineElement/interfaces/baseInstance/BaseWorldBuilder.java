package EngineElement.interfaces.baseInstance;

import EngineElement.interfaces.World;
import EngineElement.interfaces.WorldBuilder;

public abstract class BaseWorldBuilder implements WorldBuilder{
	protected World world;
	public void setWorld(World world) {
		this.world = world;
		Build();
	}
}
