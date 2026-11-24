package avaj;
import java.util.List;

public class Tower {
	private List<Flyable> observers;

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
		System.out.printf("Tower says: %s registered to weather tower", p_flyable.toString());

	}
	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		System.out.printf("Tower says: %s unregistered to weather tower", p_flyable.toString());

	}
	protected void conditionChanged() {
		for (Flyable fly: observers) {
			fly.updateConditions();
		}
	}
}