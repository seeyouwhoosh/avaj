package avaj;

import avaj.exception.*;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;

	Coordinates(int p_longitude, int p_latitude, int p_height) throws NegativeNumberException {
		if (p_latitude < 0 || p_longitude < 0 || p_height < 0) {
			throw new NegativeNumberException("Coordinates");
		}
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
	}
	Coordinates(Coordinates p_coordinates) {
		this.longitude = p_coordinates.longitude;
		this.latitude = p_coordinates.latitude;
		this.height = p_coordinates.height;
	}

	public int getLongitude() { return longitude; }
	public int getLatitude() { return latitude; }
	public int getHeight() { return height; }

	public String toString() {
		return "(" + this.longitude + ", " + this.latitude + ", " + this.height + ")";
	}
}
