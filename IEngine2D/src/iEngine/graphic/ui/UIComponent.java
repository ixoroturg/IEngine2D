package iEngine.graphic.ui;
//import  java.awt.*;

import java.util.*;

import iEngine.graphic.Model2D;

public abstract class UIComponent<T> implements Comparable<UIComponent<T>> {
	public static final byte TOP_LEFT = 1;
	public static final byte TOP = 2;
	public static final byte TOP_RIGHT = 3;
	public static final byte RIGHT = 4;
	public static final byte BOTTOM_RIGHT = 5;
	public static final byte BOTTOM = 6;
	public static final byte BOTTOM_LEFT = 7;
	public static final byte LEFT = 8;
	public static final byte CENTER = 9;
	public static final byte ABSOLUTE = 0;
	/**
	 * Дочерние компоненты выстраиваются в колонку. Если их больше, чем setMaxXChildren(), то переходят на следующую колонку
	 */
	public static final byte ROW = 0;
	/**
	 * Дочерние компоненты выстраиваются в строку. Если их больше, чем setMaxYChildren(), то переходят на следующую строку
	 */
	public static final byte COLUMN = 1;
	/**
	 * Дочерние компоненты имеют свои размеры в координатах grid
	 */
	public static final byte GRID = 2;
	/**
	 * Дочерние компоненты указывают своё местоположение: по краям, по углам, в центре или абсолютная координата
	 */
	public static final byte POLAR = 3;
	
	public static final byte START = 3;
//	public static final byte CENTER = 9;
	public static final byte END = 5;
	protected short xChildrenCount = 1;
	protected short yChildrenCount = 1;
	protected Model2D<T> model;
	protected byte rowSize = 1;
	protected byte columnSize = 1;
	protected float scale;
	public static float UIScale = 1;
//	public static final byte 
	protected float x = -1, y = -1, width = 0, height = 0;
	protected int paddingRight = 0, paddingLeft = 0, paddingTop = 0, paddingBottom = 0;
	protected byte position = 0;
	protected int zIndex = 0;
	protected byte align = 1;
	protected int gap = 0;
	protected SortedSet<UIComponent<T>> children = new TreeSet<>();
	protected int maxWidth = 0, maxHeight = 0;
	
	public UIComponent<T> setModel(Model2D<T> model) {
		this.model = model;
		return this;
	}
	public Model2D<T> getModel() {
		return model;
	}
	public UIComponent<T> setMaxWidth(int width) {
		maxWidth = width;
		return this;
	}
	public UIComponent<T> setMaxHeight(int height){
		maxHeight = height;
		return  this;
	}
	public UIComponent<T> setAlign(byte align) {
		this.align = align;
		return this;
	}
	public byte getAlign() {
		return align;
	}
	public UIComponent<T> setGap(int gap) {
		this.gap = gap;
		return this;
	}
	public float getGap() {
		return gap;
	}
	public UIComponent<T> setXMaxChildren(short count){
		xChildrenCount = count;
		return this;
	}
	public UIComponent<T> setYMaxChildren(short count){
		yChildrenCount = count;
		return this;
	}
	public short getXMaxChildren() {
		return xChildrenCount;
	}
	public short getYMaxChildren() {
		return yChildrenCount;
	}
	public UIComponent<T> setBounds(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		return this;
	}
	public UIComponent<T> setZIndex(int index) {
		zIndex = index;
		return this;
	}
	protected int doZoom(int value) {
		return (int)(value * UIComponent.UIScale * scale);
	}
	public int getZIndex() {
		return zIndex;
	}
	public UIComponent<T> setPosition(float x, float y) {
		this.x =x;
		this.y = y;
		return this;
	}
	public UIComponent<T> setPosition(byte position) {
		this.position = position;
		return this;
	}
	public byte getPosition() {
		return position;
	}
	public UIComponent<T> setSize(float width, float height) {
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
	
	@Override
	public int compareTo(UIComponent<T> comp) {
		return comp.zIndex - zIndex;
	}
	public abstract T render();
	
	
}
