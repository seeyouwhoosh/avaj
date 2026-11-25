package avaj;

public class Helicopter extends Aircraft {
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = weatherTower.getWeather(coordinates);
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		if (height == 0) {
			this.writeLog(this.toString() + " is on land\n");
			this.unregisterTower();
			return;
		}

		switch (weather) {
			case "SUN":
				longitude += 10;
				height += 2;
				this.writeLog(this.toString() + ": This is hot.\n");
				break;
			case "RAIN":
				longitude += 5;
				this.writeLog(this.toString() + ": I should have implemented windshields.\n");
				break;
			case "FOG":
				longitude +=1;
				this.writeLog(this.toString() + ": I have powerful lights.\n");
				break;
			case "SNOW":
				height -= 12;
				this.writeLog(this.toString() + ": My rotor is going to freeze!\n");
				break;
			default:
				break;
		}
		if (height > 100)
			height = 100;
		else if (height <= 0) {
			height = 0;
			this.writeLog(this.toString() + " landing\n");
			this.unregisterTower();
		}
		coordinates = new Coordinates(longitude, latitude, height);
	}
}
