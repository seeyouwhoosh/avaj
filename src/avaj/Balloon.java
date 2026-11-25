package avaj;

public class Balloon extends Aircraft {
	public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
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
				longitude += 2;
				height += 4;
				this.writeLog(this.toString() + ": Let's enjoy the good weather and take some pics.\n");
				break;
			case "RAIN":
				height -= 5;
				this.writeLog(this.toString() + ": Damn you rain! You messed up my balloon.\n");
				break;
			case "FOG":
				height -= 3;
				this.writeLog(this.toString() + ": I can't see anything...\n");
				break;
			case "SNOW":
				height -= 15;
				this.writeLog(this.toString() + ": It's snowing. We're gonna crash\n");
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
