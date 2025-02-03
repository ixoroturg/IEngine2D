package iEngine.element.interfaces.baseInstance;

import java.util.*;

import iEngine.element.interfaces.Controlable;
import iEngine.element.interfaces.World;
import iEngine.input.BaseController;
import iEngine.input.interfaces.Controller;
import iEngine.input.interfaces.Mouse;

public class BaseWorldController extends BaseController{
	private World world;
	public BaseWorldController(World world) {
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
	@Override
	public Controller setMouse(Mouse m) {
		var list = getList();
		list.forEach(controller -> {
			controller.getController().setMouse(m);
		});
		return this;
	}
}
