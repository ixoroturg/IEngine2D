package EngineOutput;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import EngineElement.World;
import EngineOutput.camera.Camera;
import EngineOutput.camera.CameraProperty.Property;

public class Window extends JFrame{
	private Panel frame;// = new Panel(new EngineOutput.camera.instance.StandartJavaCamera().setResolution(getWidth(), getHeight()));
	public Window(World w) {
		this("IEngine2D", w);
	}
	public Window() {
		this("IEngine2D", null);
	}
	public Window(String title, World world) {
		setBounds(0,0,1920,1080);
		setExtendedState(MAXIMIZED_BOTH);
		
		//System.out.println();
		//System.exit(0);
		
		Camera cam = new EngineOutput.camera.instance.StandartJavaCamera()
				.setResolution(getWidth(), getHeight())
				.setWorld(world);
		cam.getProperties().add(Property.showHitbox, new Color(255,0,0).getRGB());
		frame = new Panel(cam);
		setTitle(title);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		});
		add(frame);
		setVisible(true);
	}
}
