package iEngine.output;

import java.awt.event.*;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import iEngine.graphic.camera.Camera;

public class Window extends JFrame {

	private static final long serialVersionUID = 4170365226302678089L;
	public Panel frame;

	public Window() {
		setBounds(0, 0, 1920, 1080);
		// setExtendedState(MAXIMIZED_BOTH);

		// System.out.println();
		// System.exit(0);

		/*
		 * Camera cam = new EngineOutput.camera.instance.StandartJavaCamera()
		 * .setResolution(getWidth(), getHeight()) .setWorld(world);
		 * cam.getProperties().add(Property.showHitbox, new
		 * Color(255,0,0).getRGB());
		 */
		JFrame me = this;
		frame = new Panel();
		frame.setBounds(0, 0, getWidth(), getHeight());
		setUndecorated(false);
		addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				float w = e.getComponent().getWidth();
				float h = e.getComponent().getHeight();
				
				if(w/h != frame.camera.getRatio()) {
					w = h * frame.camera.getRatio();
				}
				frame.camera.setResolution((int)w, (int)h);
				me.setSize((int)w, (int)h);
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}

		});
		add(frame);
		setVisible(true);

//		frame.addComponentListener(new ComponentAdapter() {
//		    public void componentResized(ComponentEvent componentEvent) {
//		    	int y = 0;
//		    	int x = 0 ;
//		    	if(!isUndecorated()) {
//		    		x = getInsets().left;
//		    		y = getInsets().top;
//		    	}
//		    	int w = getContentPane().getWidth();
//		    	int h = getContentPane().getHeight();
//		    	
////		    	System.out.println(w+" "+h);
//		        frame.setBounds(0,0, w, h);
//		    }
//		});
	}
	public Window setFPS(int fps) {
		frame.setFPS(fps);
		return this;
	}
	public Window setCamera(Camera camera) {
		frame.setCamera(camera);
		return this;
	}

}
