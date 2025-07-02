package iEngine.graphic.ui;

import java.awt.*;
import java.awt.image.*;
//import iEngine.graphic.ui.Component;
//import java.util.ArrayList;
//import java.util.List;
public class UI {
	public static final byte TOP_LEFT = 1;
	public static final byte TOP = 2;
	public static final byte TOP_RIGHT = 3;
	public static final byte RIGHT = 4;
	public static final byte BOTTOM_RIGHT = 5;
	public static final byte BOTTOM = 6;
	public static final byte BOTTOM_LEFT = 7;
	public static final byte LEFT = 8;
	public static final byte CENTER = 9;
	public static final byte NONE = 0;
	
//	protected Component topLeft = new Component();
//	protected Component top = new Component();
//	protected Component topRight = new Component();
//	protected Component right = new Component();
//	protected Component bottomRight = new Component();
//	protected Component bottom = new Component();
//	protected Component bottomLeft = new Component();
//	protected Component left = new Component();
//	protected Component center = new Component();
	protected Image ui;
	protected Graphics2D gr;
//	protected List<UIComponent> components = new ArrayList<UIComponent>();
	protected int width = 0, height = 0, zoom = 1;
	public UI setResolution(int width, int height) {
		this.width = width;
		this.height = height;
		ui = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		gr = (Graphics2D) ui.getGraphics();
		return this;
	}
//	public Image render() {
//		gr.setColor(new Color(0,0,0,0));
//		gr.fillRect(0,0,width,height);
//		boolean topLeft = false, top = false, topRight = false, right = false, bottomRight = false, bottom = false, bottomLeft = false, left = false, center = false;
//		for(AbstractComponent component: components) {
//			Image img = component.render();
//			switch(component.getPosition()) {
//				case TOP_LEFT -> {
//					if(topLeft)
//						break;
////					gr.scale(zoom, zoom);
//					gr.drawImage(img,0,0,null);
//					topLeft = true;
//				}
//				case TOP -> {
//					if(top)
//						break;
//					gr.drawImage(img, width/2 - img.getWidth(null) / 2,0,null);
//					top = true;
//				}
//				case TOP_RIGHT -> {
//					if(topRight)
//						break;
//					gr.drawImage(img,width - img.getWidth(null),0,null);
//					topRight = true;
//				}
//				case RIGHT -> {
//					if(right)
//						break;
//					gr.drawImage(img,width - img.getWidth(null),height/2 - img.getHeight(null)/2,null);
//					right = true;
//				}
//				case BOTTOM_RIGHT ->{
//					if(bottomRight)
//						break;
//					gr.drawImage(img,width - img.getWidth(null), height - img.getHeight(null),null);
//					bottomRight=true;
//				}
//				case BOTTOM ->{
//					if(bottom)
//						break;
//					gr.drawImage(img,width/2 - img.getWidth(null)/2,height - img.getHeight(null),null);
//					bottom = true;
//				}
//				case BOTTOM_LEFT -> {
//					if(bottomLeft)
//						break;
//					gr.drawImage(img,0,height - img.getHeight(null),null);
//					bottomLeft = true;
//				}
//				case LEFT -> {
//					if(left)
//						break;
//					gr.drawImage(img,0,height/2 - img.getHeight(null)/2,null);
//					left = true;
//				}
//				case CENTER -> {
//					if(center)
//						break;
//					gr.drawImage(img,width/2 - img.getWidth(null)/2,height/2 - img.getHeight(null)/2,null);
//					center = true;
//				}
//				default -> {
//					float[] pos = component.getAbsolutePosition();
//					int w = (int)(pos[0] / 100 * width);
//					int h = (int)(pos[1] / 100 * height);
//					gr.drawImage(img,w,h,null);
//				}
//			}
//		}
//		return ui;
//	}
}
