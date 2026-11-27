package avaj.exception;

public class InvalidTokenException extends Exception {
	public InvalidTokenException() {
		super("Inserted token is not allowed in this context");
	}
}
