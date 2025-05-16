package iEngine.output;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import iEngine.graphic.camera.Camera;

public class Panel extends JPanel {

	private static final long serialVersionUID = 4729727103360528872L;
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
	public void setBounds(int x, int y, int w, int h) {
//		super.setBounds(x,y,w,h);
		super.setBounds(x, y, w, h);
		// super.setBounds(, y, width, height);
//		System.out.println("Размеры");
		if (camera != null)
//			camera.setResolution(w, h);
			camera.setResolution(getWidth(), getHeight());
	}
	@Override
	public void paint(Graphics gr) {
		super.paint(gr);
		Image frame = camera.render();
		gr.drawImage(frame, 0, 0, getWidth(), getHeight(), null);
	}

	private TimerTask task = new TimerTask() {

		@Override
		public void run() {
			repaint();
		}

	};

}
