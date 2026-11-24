package avaj;

public class WeatherProvider {
	private String[] weather = {"RAIN", "SNOW", "SUN", "FOG"};

	private static final WeatherProvider WEATHER_PROVIDER = new WeatherProvider();
	
	private WeatherProvider() {}

	public static WeatherProvider getInstance() {
		return WEATHER_PROVIDER;
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		int height = p_coordinates.getHeight();
		int longitude = p_coordinates.getLongitude();
		int latitude = p_coordinates.getLatitude();
		int seed = height + latitude + longitude;

		return weather[seed % 4];
	}
}