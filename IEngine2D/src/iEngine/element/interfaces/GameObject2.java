package iEngine.element.interfaces;


public interface GameObject2 {
	public void onTickChange(int tickrate);
//	public GameObject initialize(World world);
	public void onCreate();
//	public GameObject setWorld(World world);
	public World getWorld();
}
