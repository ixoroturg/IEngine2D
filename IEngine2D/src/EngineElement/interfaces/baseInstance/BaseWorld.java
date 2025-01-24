package EngineElement.interfaces.baseInstance;

import java.util.*;

import EngineElement.TickManager;
import EngineElement.interfaces.Storage;
import EngineElement.interfaces.World;
import EngineElement.interfaces.WorldBuilder;
import EngineInput.BaseController;
import EngineInput.Controller;

public class BaseWorld implements World{
	protected Map<Integer, Storage> storageMap = new TreeMap<Integer, Storage>();
	protected Storage lastStorage;
	protected Storage storage = new BaseStorage();
	protected Timer tickTimer = new Timer(true);
	protected Controller controller = new BaseController(null);
	protected TickManager tickManager = new TickManager().setWorld(this);
	
	@Override
	public Storage getStorage() {
		return storage;
	}
	@Override
	public World setWorld(Storage storage) {
		lastStorage = this.storage;
		this.storage = storage;
		return this;
	}
	@Override
	public World previosWorld() {
		this.storage = lastStorage;
		return this;
	}
	@Override
	public World saveWorld(int index) {
		storageMap.put(index, storage);
		return this;
	}
	@Override
	public World restoreWorld(int index) {
		storage = storageMap.get(index);
		return this;
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
	@Override
	public Controller getController() {
		return controller;
	}
	@Override
	public World setController(Controller controller) {
		this.controller = controller;
		return this;
	}
}
