package avaj;

public class Aircraft extends Flyable {
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		this.id = p_id;
		this.name = p_name;
		coordinates = new Coordinates(p_coordinates);
	}

	public void updateConditions() {}

	public String toString() {
		return getClass().getSimpleName() + "#" + name + "(" + id + ")";
	}
}
