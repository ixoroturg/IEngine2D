package iEngine.graphic.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
public class StandartJavaUIComponent extends UIComponent<Image>{
	
	@Override
	public Image render() {
		Image result = null;
		switch(position){ 
			case UIComponent.POLAR -> {
				Image background = model.getSprite();
				Graphics2D gr = (Graphics2D) background.getGraphics();
				int w = 0;
				int h = 0;
				for(UIComponent<Image> child: children) {
					Image img = child.render();
					switch(child.getPosition()) {
						// TOP_LEFT из  инициализации идёт
						case UIComponent.TOP -> {
							w = (int)((width - img.getWidth(null)) / 2);
						}
						case UIComponent.TOP_RIGHT -> {
							w = (int) (width - img.getWidth(null));
						}
						case UIComponent.RIGHT -> {
							w = (int) (width - img.getWidth(null));
							h = (int)((height - img.getHeight(null)) / 2);
						}
						case UIComponent.BOTTOM_RIGHT -> {
							w = (int)(width - img.getWidth(null));
							h = (int)(height - img.getHeight(null));
						}
						case UIComponent.BOTTOM -> {
							w = (int)((width - img.getWidth(null))/2);
							h = (int)(height - img.getHeight(null));
						}
						case UIComponent.BOTTOM_LEFT -> {
							h = (int)(height - img.getHeight(null));
						}
						case UIComponent.LEFT -> {
							h = (int)((height - img.getHeight(null))/2);
						}
						case UIComponent.CENTER -> {
							w = (int)((width - img.getWidth(null))/2);
							h = (int)((height - img.getHeight(null))/2);
						}
						case UIComponent.ABSOLUTE -> {
							float[] pos = child.getAbsolutePosition();
							w = (int) (pos[0] * width);
							h = (int) (pos[1] * height);
						}
					}
//					w *= UIComponent.UIScale * scale;
					w = doZoom(w);
					h = doZoom(h);
//					h *= UIComponent.UIScale * scale;
					gr.drawImage(img,w,h,null);
				}
				result = background;
			}
			case UIComponent.ROW -> {
				int w = 0;
				int h = 0;
				int currentMaxWidth = 0;
				int currentMaxHeight = 0;
				int currentMaxChild = 0;
				int childCount = 0;
				int currentMaxHeightCount = 0;
//				int currentMaxChild = childCount;
				for(UIComponent<Image> child: children) {
//					float[] size = child.getSize();
					Image img = child.render();
//					int childW = (int)(size[0] * width);
//					int childH = (int)(size[1] * height);
					int childW = img.getWidth(null);
					int childH = img.getHeight(null);
					childCount++;
					if(childH > currentMaxHeight)
						currentMaxHeight = childH;
					w += childW;
					if(w + paddingRight + paddingLeft > maxWidth || childCount == getXMaxChildren()) {
						childCount = getXMaxChildren();
						h += currentMaxHeight;
						currentMaxHeight = 0;
						if(currentMaxChild < childCount)
							currentMaxChild = childCount;
						childCount = 0;
						w -= childW;
						if(currentMaxWidth < w)
							currentMaxWidth = w;
						w = 0;
						currentMaxHeightCount++;
					}
				}
				currentMaxWidth += (currentMaxChild - 1) * gap + paddingLeft + paddingRight;
				h += (currentMaxHeightCount - 1) * gap + paddingTop + paddingBottom;
				Image img = new BufferedImage(currentMaxWidth,h,BufferedImage.TYPE_4BYTE_ABGR);
				Graphics2D gr = (Graphics2D) img.getGraphics();
				gr.drawImage(model.getSprite(),0,0,null);
				w = paddingLeft;
				h = paddingTop;
				childCount = 0;
				currentMaxHeight = 0;
				for(UIComponent<Image> child: children) {
					Image childImg = child.render();					
					childCount++;
					if(currentMaxHeight < childImg.getHeight(null))
						currentMaxHeight = childImg.getHeight(null);
					if(w + childImg.getWidth(null) + paddingRight > maxWidth || childCount == getXMaxChildren()) {
						w = paddingLeft;
						h += currentMaxHeight;
						currentMaxHeight = 0;
					}
					w = doZoom(w);
					h = doZoom(h);
					gr.drawImage(childImg,w,h,null);
				}
				result = img;
			}
		}
		return result;
	}
}
