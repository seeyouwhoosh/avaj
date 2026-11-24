package avaj;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static int simulationRuns = 0;

	private static boolean createAircraft(Scanner sc, ArrayList<Flyable> flyables) {
		String type;
		String name;
		int longitude;
		int latitude;
		int height;
	
		if (!sc.hasNext()) {
			error("Wrong format");
		}
		type = sc.next();
		if (type != "Helicopter" || type != "JetPlane" || type != "Balloon") {
			return false;
		}
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
		if (height > 100) {
			height = 100;
		}
		else if (0 < height) {
			height = 0;
		}
	
		flyables.add(AircraftFactory.getInstance().newAircraft(type, name, new Coordinates(longitude, latitude, height)));

		return true;
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
			e.printStackTrace();
			error("A problem ocurred");
		}
		return null;
	}

	private static ArrayList<Flyable> enlistAircrafts(Scanner scanner) {
		ArrayList<Flyable> flyables = new ArrayList<>();
		String line;
		while (!(line = scanner.nextLine()).isEmpty()) {
			Scanner tokensScanner = new Scanner(line);

			if (!createAircraft(tokensScanner, flyables)) {
				error("Couldn't create aircraft");
			}

		}
		return flyables;
	}

	private static void runSimulation() {
	
		for (long i = 0; i < simulationRuns; i++) {

		}

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
		ArrayList<Flyable> flyables = enlistAircrafts(scanner);
		scanner.close();

		WeatherTower tower = new WeatherTower();
		for (Flyable fly : flyables) {
			fly.registerTower(tower);
		}

		runSimulation();
	}
}
