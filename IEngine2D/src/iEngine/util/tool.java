package iEngine.util;

public class tool {
	@SuppressWarnings("unchecked")
	public static <T> T[] createGenericArray(Class<T> type, int length) {
		return (T[]) java.lang.reflect.Array.newInstance(type, length);
	}
}
