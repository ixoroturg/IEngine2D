package iEngine.input;
import java.awt.event.*;
import iEngine.math.*;
import iEngine.math.Vector;
import iEngine.output.Device;

public class StandartJavaMouse extends AbstractMouse implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	protected Vector lastMovement = new Vector(0,0);
	protected Point dragStart = new Point(0,0);
	protected Point dragEnd = new Point(0,0);
	protected Point buffer = new Point(0,0);
	protected boolean drag = false;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		buffer.set(position);
		float x = e.getX();
		float y = camera.getResolution()[1] - e.getY() ;
		int[] res = Device.getDisplayResolution();
		int[] camRes = camera.getResolution();
		x *= (float)res[0] / camRes[0];
		y *= (float)res[1] / camRes[1];
		position.set(x, y);
		
//		System.out.println("Текущая позиция: "+ buffer);
//		System.out.println("Следующая позиция: "+ position);
		position.add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		
		
		controller.press(DRAG);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		buffer.set(position);
		float x = e.getX();
		float y = camera.getResolution()[1] - e.getY() ;
		int[] res = Device.getDisplayResolution();
		int[] camRes = camera.getResolution();
		x *= (float)res[0] / camRes[0];
		y *= (float)res[1] / camRes[1];
		position.set(x, y);
		position.add(camera.getPosition());
		lastMovement = buffer.getVector(position);
		controller.press(MOVE);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		if(key == 3)
			key = 2;
		else if (key == 2)
			key = 3;
		controller.press(key);
		dragStart.set(e.getX(), e.getY()).add(camera.getPosition());	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		if(key == 3)
			key = 2;
		else if (key == 2)
			key = 3;
		controller.release(key);
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
		return false;
	}
	@Override
	public Vector getMovement() {
		return lastMovement;
	}
	@Override
	public void mouseClicked(MouseEvent e) {}	
}
