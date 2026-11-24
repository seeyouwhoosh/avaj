package avaj;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		System.out.printf("%s: ", this.toString());
		switch (weather) {
			case "SUN":
				latitude += 10;
				height += 2;
				System.out.println("I like sunny days");
				break;
			case "RAIN":
				latitude += 5;
				System.out.println("It's raining. Better watch out for lightings.");
				break;
			case "FOG":
				latitude +=1;
				System.out.println("Good thing I don't care.");
				break;
			case "SNOW":
				height -= 7;
				System.out.println("OMG! Winter is coming!");
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

