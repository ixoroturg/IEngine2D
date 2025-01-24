package EngineElement.interfaces.baseInstance;

import java.util.*;

import EngineElement.interfaces.Controlable;
import EngineElement.interfaces.World;
import EngineInput.BaseController;

public class BaseWorldController extends BaseController{
	private World world;
	public BaseWorldController(World world) {
		super(null);
		this.world = world;
	}
	private List<Controlable> getList(){
		return world.getStorage().getControlList();
	}
	@Override
	public void press(int key){
		var list = getList();
		list.forEach(controller -> {
			controller.getController().press(key);
		});
	}
	@Override
	public void release(int key){
		var list = getList();
		list.forEach(controller -> {
			controller.getController().release(key);
		});
	}
	@Override
	public void doAction(int action){
		var list = getList();
		list.forEach(controller -> {
			controller.getController().doAction(action);
		});
	}
	@Override
	public void undoAction(int action){
		var list = getList();
		list.forEach(controller -> {
			controller.getController().undoAction(action);
		});
	}
}
