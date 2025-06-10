package iEngine.input.instances;

import java.awt.event.*;

import iEngine.input.AbstractMouse;

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
		int key = switch(e.getButton()) {
			case 1 -> LMB;
			case 2 -> MOUSE_WHEEL;
			case 3 -> RMB;
			case 4 -> MOUSE4;
			case 5 -> MOUSE5;
			default -> 0;
		};
		controller.press(key);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int key = switch(e.getButton()) {
			case 1 -> LMB;
			case 2 -> MOUSE_WHEEL;
			case 3 -> RMB;
			case 4 -> MOUSE4;
			case 5 -> MOUSE5;
			default -> 0;
		};
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
//		System.out.println(e.getButton());
	}

}
