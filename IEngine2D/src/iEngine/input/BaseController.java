package iEngine.input;
import java.util.function.Consumer;

import java.util.*;

import iEngine.input.interfaces.*;

public class BaseController implements Controller {

	protected Map<Integer, List<Integer>> keyBind = new TreeMap<>();
	protected Map<Integer, List<Integer>> keyActBind = new TreeMap<>();
	protected Map<Integer, Boolean> action = new TreeMap<>();
	protected Map<Integer,Consumer<Boolean>> runs = new TreeMap<>();
	private List<ControllerListener> listeners = new LinkedList<ControllerListener>();
	protected Mouse mouse = null;

	/*
	 * public BaseController(int... actions) { if(actions == null) return;
	 * for(int act: actions) { action.put(act, false); } }
	 */
	@Override
	public Controller bind(int key, int action) {
		if (keyBind.containsKey(key)) {
			keyBind.get(key).add(action);
		} else {
			keyBind.put(key, new ArrayList<>(1));
			keyBind.get(key).add(action);
		}

		this.action.put(action, false);
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
		keyActBind.remove(key);
		return this;
	}
	@Override
	public boolean isActive(int action) {
		return this.action.get(action);
	}
	@Override
	public void press(int key) {
		List<Integer> a = keyBind.get(key);
		if(a != null) {
			a.forEach(action -> {
				this.action.put(action,true);
			});
		}
		List<Integer> r = keyActBind.get(key);
		if(r != null) {
			r.forEach(action ->{
				runs.get(action).accept(true);
			});
		}	
	}
	@Override
	public void release(int key) {
		List<Integer> a = keyBind.get(key);
		if(a != null) {
			a.forEach(action -> {
				this.action.put(action,false);
			});
		}
		List<Integer> r = keyActBind.get(key);
		if(r != null) {
			r.forEach(action ->{
				runs.get(action).accept(false);
			});
		}	
	}
	@Override
	public void doAction(int action) {
		this.action.put(action, true);
		this.runs.get(action).accept(true);
//		triggerAction(action, true);
	}
	@Override
	public void undoAction(int action) {
		this.action.put(action, false);
		this.runs.get(action).accept(false);
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
	@Override
	public Controller bind(int key, int id, Consumer<Boolean> action) {
		if (keyActBind.containsKey(key)) {
			keyActBind.get(key).add(id);
		} else {
			keyActBind.put(key, new ArrayList<>(1));
			keyActBind.get(key).add(id);
		}

		this.runs.put(id, action);
		return this;
	}
}
