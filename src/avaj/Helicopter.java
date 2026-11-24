package avaj;

public class Helicopter extends Aircraft {
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		switch (weather) {
			case "SUN":
				longitude += 10;
				height += 2;
				System.out.println("This is hot.");
				break;
			case "RAIN":
				longitude += 5;
				System.out.println("I should have implemented windshields.");
				break;
			case "FOG":
				longitude +=1;
				System.out.println("I have powerful lights.");
				break;
			case "SNOW":
				height -= 12;
				System.out.println("My rotor is going to freeze!");
				break;
			default:
				break;
		}
		coordinates = new Coordinates(longitude, latitude, height);
	}
}
