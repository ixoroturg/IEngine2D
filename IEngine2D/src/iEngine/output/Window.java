package iEngine.output;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;
//import

import iEngine.graphic.camera.Camera;

public class Window extends JFrame {

	private static final long serialVersionUID = 4170365226302678089L;
	public Panel frame;
//	private boolean widthIsMain = true;
//	private int[] lastSize = new int[2];
//	private Dimension size = new Dimension(1,1);
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
//		JFrame me = this;
		frame = new Panel();
		frame.setBounds(0, 0, getWidth(), getHeight());
		setUndecorated(false);
//		lastSize[0] = getWidth();
//		lastSize[1] = getHeight();
		
		addMouseMotionListener(null);

		addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
//				if(true)return;
				int w = e.getComponent().getWidth();
//				e.getComponent().getSize(null);
				
				int h = e.getComponent().getHeight();
//				System.out.println("Размеры: "+w+" "+h);
//				if(w/h != frame.camera.getRatio()) {
//				if(w == lastSize[0] && h == lastSize[1])
//					return;
//					
////					System.out.println(w+ " "+h);
////					System.out.println(Arrays.toString(lastSize));
//					if(w == lastSize[0]) {
////						System.out.println("Высота изменилась "+w+" "+h+" "+frame.camera.getRatio());
////						System.out.println("Высота изменилась");
//						w = (int) (h * frame.camera.getRatio());
////						System.out.println(w);
//					}
//					else if(h == lastSize[1]){
////						System.out.println("Ширина изменилась изменилась");
//						h = (int) (w / frame.camera.getRatio());
//					} else {
//						if(widthIsMain)
//							h = (int) (w / frame.camera.getRatio());
//						else
//							w = (int) (h * frame.camera.getRatio());
//							
//					}
				frame.camera.setResolution(w, h);
				frame.setSize(w, h);		
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
	public Window setCamera(Camera<Image> camera) {
		frame.setCamera(camera);
		return this;
	}

}
