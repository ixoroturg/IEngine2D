package EngineElement.interfaces;

public interface World {
	public Storage getStorage();
	public void setWorld(Storage storage);
	public void previosWorld();
	public void saveWorld(int index);
	public void restoreWorld(int index);
	
	public World setTickrate(int tickrate);
	public void doTick();
}
