package iEngine.element.interfaces;

public interface Tickable {

	public void onTick();
	default public void onTickChange(int tickrate) {}
	// public default void onTickChange(int tickrate) {};
}
