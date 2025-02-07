package iEngine.element.interfaces;

public interface Tickable {
	public void onTick();
	public default void onTickChange(int tickrate) {};
}
