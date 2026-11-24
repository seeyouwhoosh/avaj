package avaj;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions();

	public void registerTower(WeatherTower p_tower) {
		p_tower.register(this);
		weatherTower = p_tower;
	}

	public void unregisterTower() {
		weatherTower.unregister(this);
		weatherTower = null;
	}
}