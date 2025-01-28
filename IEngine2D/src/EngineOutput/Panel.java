package EngineOutput;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import EngineOutput.camera.Camera;
public class Panel extends JPanel {
	private Timer fps = new Timer(true);
	private Camera camera;
	public Panel setCamera(Camera camera) {
		this.camera = camera;
		camera.setResolution(getWidth(), getHeight());
		return this;
	}
	public void setFPS(int fps) {
		this.fps.cancel();
		this.fps = new Timer(true);
		this.fps.scheduleAtFixedRate(task, 0, 1000 / fps);
	}
	@Override
	public void paint(Graphics gr) {
		super.paint(gr);
		gr.drawImage(camera.render(), 0, 0, null);
	}
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			repaint();
		}};
}
