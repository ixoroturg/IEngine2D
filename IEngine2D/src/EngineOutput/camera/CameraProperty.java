package EngineOutput.camera;

import java.util.Map;
import java.util.TreeMap;
/**
 * Особые настройки камеры
 */
public class CameraProperty {
	/**
	 * Карта настроек и их значений
	 */
	protected Map<Property, Integer> properties = new TreeMap<Property, Integer>();
	/**
	 * Добавляет настройку и её значение
	 * @param property
	 * @param value
	 */
	public void add(Property property, int value) {
		properties.put(property, value);
		//new java.awt.Color(0,0,0).get
	}
	/**
	 * Удаляет настройку
	 * @param property
	 */
	public void remove(Property property) {
		properties.remove(property);
	}
	/**
	 * 
	 * @param property
	 * @return значение настройки
	 */
	public int get(Property property) {
		return properties.get(property);
	}
	/**
	 * 
	 * @param property
	 * @return true если такая настройка есть
	 */
	public boolean isHave(Property property) {
		return properties.containsKey(property);
	}
	public static enum Property{
		/**
		 * Показывать рёбра хитбоксов<br>
		 * Значение - 32-ух битный цвет Alpha Red Green Blue, например
		 * java.awt.Color.getRGB()
		 */
		showHitbox
	}
}
