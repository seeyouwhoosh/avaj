package avaj;
import java.io.FileWriter;
import java.util.ArrayList;

public class Tower {
	private ArrayList<Flyable> observers = new ArrayList<>();
	public FileWriter logReportWriter;

	public void setLogReporter(FileWriter p_FileWriter) {
		logReportWriter = p_FileWriter;
	}

	public void writeLog(String msg) {
		try {
			logReportWriter.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
		this.writeLog("Tower says: " + p_flyable.toString() + " registered to weather tower\n");

	}
	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		this.writeLog("Tower says: " + p_flyable.toString() + " unregistered from weather tower\n");

	}
	protected void conditionChanged() {
		for (Flyable fly: new ArrayList<>(observers)) {
			fly.updateConditions();
		}
	}
}