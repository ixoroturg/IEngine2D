package iEngine.element.interfaces.baseInstance;
import java.lang.reflect.*;
import java.util.*;
import iEngine.element.*;
import iEngine.element.interfaces.*;
import iEngine.input.BaseController;
import iEngine.input.interfaces.Controller;

public class BaseWorld implements World{
	protected Map<Integer, Storage> storageMap = new TreeMap<Integer, Storage>();
	protected Storage lastStorage;
	protected Storage storage = new BaseStorage();
	protected Timer tickTimer = new Timer(true);
	protected Controller controller = new BaseWorldController(this);
	protected Tickable tickManager = new BaseTickManager().setWorld(this);
	protected int tickrate = 0;
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
		this.tickrate = tickrate;
		//Вызов GameObject.setTickrate на всех объектах
		storage.getGameObjectList().forEach(gameObject -> {
			gameObject.onTickChange();
		});
		return this;
	}
	@Override
	public World startTickrate() {
		tickTimer.cancel();
		tickTimer = new Timer(true);
		tickTimer.scheduleAtFixedRate(getTask(), 0, 1000 / tickrate);
		return this;
	}
	protected TimerTask getTask() { return new TimerTask(){
		@Override
		public void run() {
			doTick();
		}};
	}
	@Override
	public Controller getController() {
		return controller;
	}
	@Override
	public World setController(Controller controller) {
		this.controller = controller;
		return this;
	}
	@Override
	public World setTickManager(Tickable tickManager) {
		this.tickManager = tickManager;
		return this;
	}
	@Override
	public Tickable getTickManager() {
		return tickManager;
	}
	@Override
	public int getTickrate() {
		return tickrate;
	}
}
