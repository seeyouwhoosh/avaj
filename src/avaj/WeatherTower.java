package avaj;
public class WeatherTower extends Tower {
	public void register(Flyable p_flyable) {
		super.register(p_flyable);
		System.out.printf("%s registered to weather tower", p_flyable.toString());
	}
	public void unregister(Flyable p_flyable) {
		super.unregister(p_flyable);
		System.out.printf("%s unregistered to weather tower", p_flyable.toString());
	}
	public String getWeather(Coordinates p_coordinates) {
		;
	}
	public void changeWeather(Weather p_weather) {
		;
	}
}