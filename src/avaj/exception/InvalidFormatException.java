package avaj.exception;

public class InvalidFormatException extends Exception {
	public InvalidFormatException(String obj, String type) {
		super(obj + " only accepts " + type + "type");
	}
}
