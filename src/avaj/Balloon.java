package avaj;

public class Balloon extends Aircraft {
	public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		System.out.printf("%s", this.toString());
		switch (weather) {
			case "SUN":
				longitude += 2;
				height += 4;
				System.out.println(": Let's enjoy the good weather and take some pics.");
				break;
			case "RAIN":
				height -= 5;
				System.out.println(": Damn you rain! You messed up my balloon.");
				break;
			case "FOG":
				height -= 3;
				System.out.println(": I can't see anything...");
				break;
			case "SNOW":
				height -= 15;
				System.out.println(": It's snowing. We're gonna crash");
				break;
			default:
				break;
		}
		if (height > 100)
			height = 100;
		else if (height <= 0) {
			height = 0;
			System.out.println(this.toString() + " landing");
			this.unregisterTower();
		}
		coordinates = new Coordinates(longitude, latitude, height);
	}
}
