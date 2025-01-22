package Output;

import java.awt.*;
import javax.swing.*;

public class Camera extends JPanel{
	public Camera() {
		Thread th = new Thread(() -> {
			repaint();
		});
	}
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		
	}
}
