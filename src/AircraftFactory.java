
public class AircraftFactory {
	private static int numAircrafts = 1;

	public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		return switch (p_type) {
			case "Helicopter" -> new Helicopter(numAircrafts++, p_name, p_coordinates);
			case "JetPlane" -> new JetPlane(numAircrafts++, p_name, p_coordinates);
			case "Baloon" -> new Baloon(numAircrafts++, p_name, p_coordinates);
			case null, default -> null;
		};
	}
}