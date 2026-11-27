package avaj.exception;

public class InexistentAircraftException extends Exception {
	public InexistentAircraftException(String name) {
		super(name + " is not an existing aircraft");
	}
}
