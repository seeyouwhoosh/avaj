package avaj;
import java.util.ArrayList;

public class Tower {
	private ArrayList<Flyable> observers = new ArrayList<>();

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
		System.out.printf("Tower says: %s registered to weather tower\n", p_flyable.toString());

	}
	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		System.out.printf("Tower says: %s unregistered from weather tower\n", p_flyable.toString());

	}
	protected void conditionChanged() {
		for (Flyable fly: new ArrayList<>(observers)) {
			fly.updateConditions();
		}
	}
}