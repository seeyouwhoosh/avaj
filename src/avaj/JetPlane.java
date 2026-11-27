package avaj;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
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
				latitude += 10;
				height += 2;
				this.writeLog(this.toString() + ": I like sunny days\n");
				break;
			case "RAIN":
				latitude += 5;
				this.writeLog(this.toString() + ": It's raining. Better watch out for lightings.\n");
				break;
			case "FOG":
				latitude +=1;
				this.writeLog(this.toString() + ": Good thing I don't care.\n");
				break;
			case "SNOW":
				height -= 7;
				this.writeLog(this.toString() + ": OMG! Winter is coming!\n");
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

		try {
			coordinates = new Coordinates(longitude, latitude, height);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(1);
		}
	}
}

