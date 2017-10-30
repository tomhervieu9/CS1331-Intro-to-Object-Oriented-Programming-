public class NoSuchUserException extends Exception {
	
	public NoSuchUserException() {
		super("User id does not exist.");
	}
}