package iEngine.element;

import java.util.function.Function;

import iEngine.math.SpeedFunction;

public class GlobalSettings {
	public static Function<Float,Float> defaultSpeedFunction = SpeedFunction::linear;
}
