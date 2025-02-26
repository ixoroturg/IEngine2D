package iEngine.output;

import java.awt.Dimension;

public class Device {
		private static int[] resolution = new int[2];
	public static int[] getDisplayResolution() {
		return resolution;
	}
	public static int[] getNativeResolution() {
		int[] result = new int[2];
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		result[0] = screen.width;
		result[1] = screen.height;
		return result;
	}
	public static void setResolution(int width, int height) {
		resolution[0] = width;
		resolution[1] = height;
	}
	public static void toNativeResolution() {
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		resolution[0] = screen.width;
		resolution[1] = screen.height;
	}
}
