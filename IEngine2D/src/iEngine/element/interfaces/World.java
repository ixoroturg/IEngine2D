package iEngine.element.interfaces;

import iEngine.input.interfaces.Controller;

public interface World extends Controlable{
	public Storage getStorage();
	public World setWorld(Storage storage);
	
	public World previosWorld();
	public World saveWorld(int index);
	public World restoreWorld(int index);
	
	public World setController(Controller controller);
	
	public World setTickrate(int tickrate);
	public int getTickrate();
	public World startTickrate();
	public void doTick();
	
	public World setTickManager(Tickable tickManager);
	public Tickable getTickManager();
	
	public World initialize(GameObject gameObject);
}
