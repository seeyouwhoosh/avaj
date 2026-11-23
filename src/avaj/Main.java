import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;

package avaj;

public class Main {
	static int simulationRuns = 0;

	private class AircraftData {
		String type;
		String name;
		int longitude;
		int latitude;
		int height;

		boolean verifyData() {
			if (type != "Helicopter" || type != "JetPlane" || type != "Balloon") {
				return false;
			}
			if (height > 100) {
				height = 100;
			}
			else if (0 < height) {
				height = 0;
			}
			return true;
		}

		void getData(Scanner sc) {
			if (!sc.hasNext()) {
				error("Wrong format");
			}
			type = sc.next();
			if (!sc.hasNext()) {
				error("Wrong format");
			}
			name = sc.next();
			if (!sc.hasNextInt()) {
				error("Wrong format");
			}
			longitude = sc.nextInt();
			if (!sc.hasNextInt()) {
				error("Wrong format");
			}
			latitude = sc.nextInt();
			if (!sc.hasNextInt()) {
				error("Wrong format");
			}
			height = sc.nextInt();
		}
	}

	public static void error(String msg) {
		System.out.println(msg);
		System.exit(1);
	}

	private static Scanner fileOpener(String[] args) {
		File file = new File(args[1]);
		if (!file.exists() || !file.isFile() || !file.canRead() || file.length() == 0) {
			error("Invalid file");
		}
	
		try {
			Scanner scanner = new Scanner(file);
			if (!scanner.hasNextInt()) {
				error("Invalid token");
			}
			simulationRuns = scanner.nextInt();
			if (simulationRuns <= 0) {
				error("Simulation runs ought to be >0");
			}
			return scanner;
		} catch (Exception e) {
			error("A problem ocurred");
		}
		return null;
	}

	public static void main(String[] args) {

		if (args.length != 2) {
			error("Invalid arguments");
		}

		Scanner scanner = fileOpener(args);

		String line = scanner.next();
		if (!line.isEmpty()) {
			error("First line shouldn\'t contain more than the number of simulation runs");
		}

		List<Flyable> flyables;
		AircraftFactory factory;
		while (!(line = scanner.nextLine()).isEmpty()) {
			Scanner tokensScanner = new Scanner(line);
			AircraftData data = new AircraftData();

			data.getData(tokensScanner);
			if (!scanner.next().isEmpty()) {
				error("Extra tokens in line");
			}
			if (!data.verifyData()) {
				error("Couldn't create aircraft");
			}
			flyables.add(factory.newAircraft(data.type, data.name, new Coordinates(data.longitude, data.latitude, data.height)));
		}
		scanner.close();

		Tower tower = new Tower();
		for (Flyable fly : flyables) {
			tower.register(fly);
		}
	}
}
