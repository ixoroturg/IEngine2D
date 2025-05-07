package iEngine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import iEngine.input.interfaces.Controller;
import iEngine.input.interfaces.Keyboard;

public class StandartJavaKeyboard implements KeyListener, Keyboard {

	protected Controller controller;

	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		controller.press(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		controller.release(e.getKeyCode());
	}
	@Override
	public Keyboard setController(Controller controller) {
		this.controller = controller;
		return this;
	}
	@Override
	public Controller getController() {
		return controller;
	}

}
