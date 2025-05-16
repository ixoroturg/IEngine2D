package iEngine.test;


public class TestFailedException extends Exception{
	private static final long serialVersionUID = -4153576040049718844L;

	public TestFailedException(String description){
		super(description);
	}
}
