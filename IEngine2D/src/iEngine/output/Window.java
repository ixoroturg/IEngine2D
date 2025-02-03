package iEngine.output;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import iEngine.element.interfaces.World;
import iEngine.output.camera.Camera;
import iEngine.output.camera.CameraProperty.Property;

public class Window extends JFrame{
	public Panel frame;

	public Window() {
		setBounds(0,0,1920,1080);
		setExtendedState(MAXIMIZED_BOTH);
		
		//System.out.println();
		//System.exit(0);
		
		/*Camera cam = new EngineOutput.camera.instance.StandartJavaCamera()
				.setResolution(getWidth(), getHeight())
				.setWorld(world);
		cam.getProperties().add(Property.showHitbox, new Color(255,0,0).getRGB());
		*/
		frame = new Panel();
		frame.setBounds(0,0, getWidth(), getHeight());
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
	public Window setFPS(int fps) {
		frame.setFPS(fps);
		return this;
	}
	public Window setCamera(Camera camera) {
		frame.setCamera(camera);
		return this;
	}
}
