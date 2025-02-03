package iEngine.input;
import java.awt.event.*;
import java.util.*;

import iEngine.math.*;
import iEngine.math.Vector;
import iEngine.output.camera.Camera;
public class StandartJavaMouse extends AbstractMouse implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	protected boolean drag = false;
	protected Vector lastMovement = new Vector(0,0);
	protected Point dragStart = new Point(0,0);
	protected Point dragEnd = new Point(0,0);
	protected Point buffer = new Point(0,0);
	
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
	public void mousePressed(MouseEvent e) {
		controller.press(e.getButton());
		dragStart.set(e.getX(), e.getY()).add(camera.getPosition());	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.release(e.getButton());
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
		controller.press(key);
	}
	@Override
	public Point getPosition() {
		return position;
	}
	@Override
	public Point getDragStart() {
		return dragStart;
	}
	@Override
	public Point getDragStop() {
		return dragEnd;
	}
	@Override
	public boolean isDrag() {
		return drag;
	}
	@Override
	public Vector getMovement() {
		return lastMovement;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}	
}
