package EngineInput;
import java.awt.event.*;
import java.util.*;

import EngineMath.*;
import EngineMath.Vector;
import EngineOutput.camera.Camera;
public class Mouse implements MouseListener, MouseMotionListener{
	public enum MouseButton{
		Mouse1, Mouse2, Mouse3, Mouse4, Mouse5, WheelUp, WheelDown
	}
	public Point position = new Point(0,0);
	public boolean drag = false;
	public Map<MouseButton, Boolean> button = new TreeMap<MouseButton, Boolean>();
	public Vector lastMovement = new Vector(0,0);
	private List<ClickListener> listener = new LinkedList<ClickListener>();
	public Camera camera = null;
	public Point dragStart = new Point(0,0);
	public Point dragEnd = new Point(0,0);
	
	private Point buffer = new Point(0,0);
	
	public Mouse(Camera camera) {
		this.camera = camera;
		button.put(MouseButton.Mouse1, false);
		button.put(MouseButton.Mouse2, false);
		button.put(MouseButton.Mouse3, false);
		button.put(MouseButton.Mouse4, false);
		button.put(MouseButton.Mouse5, false);
		button.put(MouseButton.WheelUp, false);
		button.put(MouseButton.WheelDown, false);
	}
	public void addClickListener(ClickListener listener) {
		this.listener.add(listener);
	}
	public void removeClickListener(ClickListener listener) {
		this.listener.remove(listener);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		buffer.set(position);
		position.set(e.getX(), e.getY()).add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		drag = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		buffer.set(position);
		position.set(e.getX(), e.getY()).add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		drag = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.forEach(l -> {l.onClick(e);});
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		button.put(MouseButton.values()[key],true);
		dragStart.set(e.getX(), e.getY()).add(camera.getPosition());	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		button.put(MouseButton.values()[key],false);
		dragEnd.set(e.getX(), e.getY()).add(camera.getPosition());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
