package avaj;
public class WeatherTower extends Tower {
	public void register(Flyable p_flyable) {
		super.register(p_flyable);
		System.out.printf("%s#%s(%d) registered to weather tower", );
	}
	public void unregister(Flyable p_flyable) {
		super.unregister(p_flyable);
	}
	public String getWeather(Coordinates p_coordinates) {
		;
	}
	public void changeWeather(Weather p_weather) {
		;
	}
}