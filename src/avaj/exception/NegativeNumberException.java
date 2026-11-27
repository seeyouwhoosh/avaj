package avaj.exception;

public class NegativeNumberException extends Exception {
	public NegativeNumberException(String obj) {
		super(obj + " only allows positive numbers");
	}
}
