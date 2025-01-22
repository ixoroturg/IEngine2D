package Input;
import java.awt.event.*;
import java.util.*;

import EngineMath.*;
public class Mouse implements MouseListener, MouseMotionListener{
	public enum Button{
		Mouse1, Mouse2, Mouse3, Mouse4, Mouse5, WheelUp, WheelDown
	}
	public Point worldPosition = new Point(0,0);
	public Point screenPosition = new Point(0,0);
	public boolean drag = false;
	public Map<Button, Boolean> button = new TreeMap<Button, Boolean>();
	private List<ClickListener> listener = new LinkedList<ClickListener>();
	public Mouse() {
		button.put(Button.Mouse1, false);
		button.put(Button.Mouse2, false);
		button.put(Button.Mouse3, false);
		button.put(Button.Mouse4, false);
		button.put(Button.Mouse5, false);
		button.put(Button.WheelUp, false);
		button.put(Button.WheelDown, false);
	}
	public void addClickListener(ClickListener listener) {
		this.listener.add(listener);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		screenPosition.set(e.getX(), e.getY());
		drag = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		screenPosition.set(e.getX(), e.getY());
		drag = false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		listener.forEach(l -> {l.click(e);});
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
		button.put(Button.values()[key],true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int key = e.getButton();
		button.put(Button.values()[key],true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
