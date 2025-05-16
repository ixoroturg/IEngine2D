package iEngine.util;


public class Pointer<T> implements Cloneable{
	public T value;
	public Pointer(T value) {
		this.value = value;
	}
	public Pointer<T> clone(){
		return new Pointer<T>(value);
	}
}
