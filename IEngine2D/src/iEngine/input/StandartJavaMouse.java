package iEngine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import iEngine.math.Point;
import iEngine.math.Vector;

public class StandartJavaMouse extends AbstractMouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	@Override
	public void mouseDragged(MouseEvent e) {

		int[] res = camera.getResolution();
		float[] size = camera.getSize();
		float x = e.getX();
		float y = res[1] - e.getY();

		x = (x / res[0] * 2 - 1) * size[0] / 2;
		y = (y / res[1] * 2 - 1) * size[1] / 2;

		position.set(x, y);

		position.add(camera.getPosition());

		controller.press(DRAG);
		controller.setPointer(position);
	}
	@Override
	public void mouseMoved(MouseEvent e) {

		int[] res = camera.getResolution();
		float[] size = camera.getSize();
		float x = e.getX();
		float y = res[1] - e.getY();

		x = (x / res[0] * 2 - 1) * size[0] / 2;
		y = (y / res[1] * 2 - 1) * size[1] / 2;
		
		position.set(x, y);
		position.add(camera.getPosition());
		controller.press(MOVE);
		controller.setPointer(position);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		if (key == 3)
			key = 2;
		else
			if (key == 2)
				key = 3;
		controller.press(key);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		if (key == 3)
			key = 2;
		else
			if (key == 2)
				key = 3;
		controller.release(key);
	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// стандартная реализация: - от человека вверх, + к человеку вниз
		int key = e.getWheelRotation() < 0 ? WHEEL_UP : WHEEL_DOWN;
		controller.press(key);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getButton());
	}

}
