package iEngine.input.interfaces;

import java.util.function.Consumer;

public interface Controller {

	public Controller bind(int key, int action);
	public Controller bind(int key, int id, Consumer<Boolean> action);

	public Controller unbind(int key, int actionOrId);
	public Controller unbind(int key);

	public boolean isActive(int action);

	public void press(int key);

	public void release(int key);

	public void doAction(int action);

	public void undoAction(int action);

	public Controller setMouse(Mouse m);

	public Mouse getMouse();

}
