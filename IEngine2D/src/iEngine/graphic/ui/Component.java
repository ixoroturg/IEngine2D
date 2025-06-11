package iEngine.graphic.ui;
import  java.awt.*;

import iEngine.graphic.Model2D;

public class Component {
	public static final byte ROW = 0;
	public static final byte COLUMN = 1;
	public static final byte GRID = 2;
	
	public static final byte START = 3;
	public static final byte CENTER = 4;
	public static final byte END = 5;
	
	protected Model2D model;
	protected byte rowSize = 1;
	protected byte columnSize = 1;
//	public static final byte 
	protected float x = -1, y = -1, width = 0, height = 0;
	protected float marginX = 0, marginY = 0, marginTop = 0, marginBottom = 0;
	protected byte position = 0;
	protected int zIndex = 0;
	protected byte align = 1;
	protected float gap = 0;
	public Component setModel(Model2D model) {
		this.model = model;
		return this;
	}
	public Model2D getModel() {
		return model;
	}
	public Component setAlign(byte align) {
		this.align = align;
		return this;
	}
	public byte getAlign() {
		return align;
	}
	public Component setGap(float gap) {
		this.gap = gap;
		return this;
	}
	public float getGap() {
		return gap;
	}
	public Component setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		return this;
	}
	public Component setZIndex(int index) {
		zIndex = index;
		return this;
	}
	public int getZIndex() {
		return zIndex;
	}
	public Component setPosition(float x, float y) {
		this.x =x;
		this.y = y;
		return this;
	}
	public Component setPosition(byte position) {
		this.position = position;
		return this;
	}
	public byte getPosition() {
		return position;
	}
	public Component setSize(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}
	public float[] getBounds() {
		return new float[] {x,y,width,height};
//		UI.CENTER++;
//		UI.TOP
	}
	public float[] getAbsolutePosition() {
		return new float[] {x,y};
	}
	public float[] getSize() {
		return new float[] {width,height};
	}
	public Image render() {
		return null;
	}
	
}
