package EngineInput;

import java.util.*;

public class BaseController implements Controller{
	
	protected Map<Integer, List<Integer>> keyBind = new TreeMap<Integer, List<Integer>>();
	protected Map<Integer, Boolean> action = new TreeMap<Integer, Boolean>();
	private List<ActionListener> listeners = new LinkedList<ActionListener>();
	protected Mouse mouse = null;
	
	public BaseController(int... actions) {
		if(actions == null)
			return;
		for(int act: actions) {
			action.put(act, false);
		}
	}
	@Override
	public Controller bind(int key, int action) {
		if(keyBind.containsKey(key)) {
			keyBind.get(key).add(action);
		} else {
			keyBind.put(key, List.of(action));
		}
		return this;
	}
	@Override
	public Controller unbind(int key, int action) {
		keyBind.get(key).remove(action);
		return this;
	}
	@Override
	public Controller unbind(int key) {
		keyBind.remove(key);
		return this;
	}
	@Override
	public boolean isActive(int action) {
		return this.action.get(action);
	}

	@Override
	public void press(int key) {	
		keyBind.get(key).forEach(action -> {
			this.action.put(action, true);
			triggerAction(action);
		});
	}
	@Override
	public void release(int key) {
		keyBind.get(key).forEach(action -> {
			this.action.put(action, false);
		});	
	}
	@Override
	public void doAction(int action) {	
		this.action.put(action, true);
		triggerAction(action);
	}
	@Override
	public void undoAction(int action) {
		this.action.put(action, false);
	}
	@Override
	public Controller setMouse(Mouse m) {
		mouse = m;
		return this;
	}
	@Override
	public Mouse getMouse() {
		return mouse;
	}
	private void triggerAction(int action) {
		listeners.forEach(listener -> {
			listener.onAction(action);
		});
	}
	@Override
	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}
	@Override
	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}
}
