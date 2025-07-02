package iEngine.graphic.ui;

import java.awt.*;
public class StandartJavaUIComponent extends UIComponent<Image>{
	
	@Override
	public Image render() {
		switch(position){ 
			case UIComponent.POLAR -> {
				Image background = model.getSprite();
				Graphics2D gr = (Graphics2D) background.getGraphics();
				for(UIComponent<Image> child: children) {
					Image img = child.render();
					switch(child.getPosition()) {
						case UIComponent.TOP_LEFT -> {
							gr.drawImage(child.render(),0,0,null);
						}
						case UIComponent.TOP -> {
							gr.drawImage(img, (int)((width - img.getWidth(null)) / 2), 0,null);
						}
						case UIComponent.TOP_RIGHT -> {
							gr.drawImage(img,(int) (width - img.getWidth(null)),0,null);
						}
						case UIComponent.RIGHT -> {
							gr.drawImage(img,(int) (width - img.getWidth(null)),(int)((height - img.getHeight(null)) / 2),null);
						}
						case UIComponent.BOTTOM_RIGHT -> {
							gr.drawImage(img, (int)(width - img.getWidth(null)), (int)(height - img.getHeight(null)),null);
						}
						case UIComponent.BOTTOM -> {
							gr.drawImage(img,(int)((width - img.getWidth(null))/2),(int)(height - img.getHeight(null)),null);
						}
						case UIComponent.BOTTOM_LEFT -> {
							gr.drawImage(img, 0, (int)(height - img.getHeight(null)),null);
						}
						case UIComponent.LEFT -> {
							gr.drawImage(img,0,(int)((height - img.getHeight(null))/2),null);
						}
						case UIComponent.CENTER -> {
							gr.drawImage(img, (int)((width - img.getWidth(null))/2),(int)((height - img.getHeight(null))/2),null);
						}
						case UIComponent.ABSOLUTE -> {
							float[] pos = child.getAbsolutePosition();
							int x = (int) (pos[0] * width);
							int y = (int) (pos[1] * height);
							gr.drawImage(img,x,y,null);
						}
					}
				}
			}
			case UIComponent.ROW -> {
				int w = 0;
				int h = 0;
				for(UIComponent<Image> child: children) {
					w += child.getSize();
				}
			}
		}
		return null;
	}
}
