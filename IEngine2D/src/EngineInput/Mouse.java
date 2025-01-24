package EngineInput;
import java.awt.event.*;
import java.util.*;

import EngineMath.*;
import EngineMath.Vector;
import EngineOutput.camera.Camera;
public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{
	public static int 
			MOUSE1 = 1,
			MOUSE2 = 2,
			MOUSE3 = 3,
			MOUSE4 = 4,
			MOUSE5 = 5,
			WHEEL_UP = 6,
			WHEEL_DOWN = 7;
			
	public Point position = new Point(0,0);
	public boolean drag = false;
	public Map<Integer, Boolean> button = new TreeMap<Integer, Boolean>();
	public Vector lastMovement = new Vector(0,0);
	private List<ClickListener> listener = new LinkedList<ClickListener>();
	public Camera camera = null;
	public Point dragStart = new Point(0,0);
	public Point dragEnd = new Point(0,0);
	
	private Point buffer = new Point(0,0);
	
	public Mouse() {
		button.put(1, false);
		button.put(2, false);
		button.put(3, false);
		button.put(4, false);
		button.put(5, false);
	}
	public Mouse setCamera(Camera camera) {
		this.camera = camera;
		return this;
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
		position.set(e.getX(), camera.getResolution()[1] - e.getY()).add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		drag = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		buffer.set(position);
		position.set(e.getX(), camera.getResolution()[1] - e.getY()).add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		drag = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.forEach(l -> {l.onClick(e.getButton());});
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		button.put(key,true);
		dragStart.set(e.getX(), e.getY()).add(camera.getPosition());	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		button.put(key,false);
		dragEnd.set(e.getX(), e.getY()).add(camera.getPosition());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// - от человека вверх, + к человеку вниз
		int key = e.getWheelRotation() < 0 ? WHEEL_UP : WHEEL_DOWN;
		listener.forEach(l -> {l.onClick(key);});
		button.put(key, null);
	}
	
}
