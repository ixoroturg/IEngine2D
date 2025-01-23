package EngineInput;

public interface Controller {
	
	public Controller bind(int key, int action);
	public Controller unbind(int key, int action);
	public Controller unbind(int key);
	
	public boolean isActive(int key);
	public void press(int key);
	public void release(int key);
	
	public void doAction(int action);
	public void undoAction(int action);
	
	public void setMouse(Mouse m);
	public Mouse getMouse();
	
	public void addActionListener(ActionListener listener);
	public void removeActionListener(ActionListener listener);
}
