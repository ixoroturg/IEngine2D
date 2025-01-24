package EngineElement.interfaces;

import EngineInput.Controller;

public interface World extends Controlable{
	public Storage getStorage();
	public World setWorld(Storage storage);
	
	public World previosWorld();
	public World saveWorld(int index);
	public World restoreWorld(int index);
	
	public World setController(Controller controller);
	
	public World setTickrate(int tickrate);
	public void doTick();
	
	
}
