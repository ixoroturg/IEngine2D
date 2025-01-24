package EngineElement.interfaces.baseInstance;

import java.util.*;

import EngineElement.TickManager;
import EngineElement.interfaces.Storage;
import EngineElement.interfaces.World;

public class BaseWorld implements World{
	protected Map<Integer, Storage> storageMap = new TreeMap<Integer, Storage>();
	protected Storage lastStorage;
	protected Storage storage;
	protected Timer tickTimer = new Timer(true);
	protected TickManager tickManager = new TickManager().setWorld(this);
	@Override
	public Storage getStorage() {
		return storage;
	}
	@Override
	public void setWorld(Storage storage) {
		lastStorage = this.storage;
		this.storage = storage;
	}
	@Override
	public void previosWorld() {
		this.storage = lastStorage;
	}
	@Override
	public void saveWorld(int index) {
		storageMap.put(index, storage);
	}
	@Override
	public void restoreWorld(int index) {
		storage = storageMap.get(index);
	}
	@Override
	public void doTick() {
		tickManager.onTick();
	}
	@Override
	public World setTickrate(int tickrate) {
		tickTimer.cancel();
		tickTimer = new Timer(true);
		tickTimer.scheduleAtFixedRate(tickTask, 0, 1000 / tickrate);
		return this;
	}
	protected TimerTask tickTask = new TimerTask(){
		@Override
		public void run() {
			doTick();
		}};
}
