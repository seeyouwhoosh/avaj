package avaj;

public class AircraftFactory {
	private static int numAircrafts = 1;

	private static final AircraftFactory AIRCRAFT_FACTORY = new AircraftFactory();

	public static AircraftFactory getInstance() {
		return AIRCRAFT_FACTORY;
	}

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		return switch (p_type) {
			case "Helicopter" -> new Helicopter(numAircrafts++, p_name, p_coordinates);
			case "JetPlane" -> new JetPlane(numAircrafts++, p_name, p_coordinates);
			case "Balloon" -> new Balloon(numAircrafts++, p_name, p_coordinates);
			case null, default -> null;
		};
	}
}