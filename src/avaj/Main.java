package avaj;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Main {
	private static int simulationRuns = 0;

	private static boolean createAircraft(Scanner sc, ArrayList<Flyable> flyables) {
		String type;
		String name;
		int longitude;
		int latitude;
		int height;
	
		if (!sc.hasNext())
			error("Wrong format");
		type = sc.next();
		if (type.compareTo("Helicopter") != 0 && type.compareTo("JetPlane") != 0 && type.compareTo("Balloon") != 0)
			return false;
		if (!sc.hasNext())
			error("Wrong format");
		name = sc.next();
		if (!sc.hasNextInt())
			error("Wrong format");
		longitude = sc.nextInt();
		if (longitude < 0)
			error("Only positive numbers");
		if (!sc.hasNextInt())
			error("Wrong format");
		latitude = sc.nextInt();
		if (latitude < 0)
			error("Only positive numbers");
		if (!sc.hasNextInt())
			error("Wrong format");
		height = sc.nextInt();
		if (height < 0)
			error("Only positive numbers");
		else if (height > 100)
			height = 100;
		if (sc.hasNextLine())
			error("Invalid token");
	
		Flyable fly = AircraftFactory.getInstance().newAircraft(type, name, new Coordinates(longitude, latitude, height));
		if (fly == null)
			error("Not an aircraft");
		flyables.add(fly);

		return true;
	}

	public static void error(String msg) {
		System.out.println(msg);
		System.exit(1);
	}

	private static Scanner fileOpener(String[] args) {
		File file = new File(args[0]);
		if (!file.exists() || !file.isFile() || !file.canRead() || file.length() == 0)
			error("Invalid file");
	
		try {
			Scanner scanner = new Scanner(file);
			if (!scanner.hasNextInt())
				error("Invalid token");
			simulationRuns = scanner.nextInt();
			if (simulationRuns <= 0)
				error("Simulation runs ought to be >0");
			return scanner;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static ArrayList<Flyable> enlistAircrafts(Scanner scanner) {
		ArrayList<Flyable> flyables = new ArrayList<>();
		String line;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			Scanner tokensScanner = new Scanner(line);

			if (!createAircraft(tokensScanner, flyables))
				error("Couldn't create aircraft");

		}
		return flyables;
	}

	private static void runSimulation(WeatherTower tower) {
		for (long i = 0; i < simulationRuns; i++)
			tower.conditionChanged();
	}

	public static void main(String[] args) {
		if (args.length != 1)
			error("Invalid arguments");

		Scanner scanner = fileOpener(args);
		String line = scanner.nextLine();
		if (!line.isEmpty())
			error("First line shouldn\'t contain more than the number of simulation runs");
		ArrayList<Flyable> flyables = enlistAircrafts(scanner);
		scanner.close();

		try {
			WeatherTower tower = new WeatherTower();
			FileWriter writer = new FileWriter("simulation.txt", false);
			tower.setLogReporter(writer);

			for (Flyable fly : flyables)
				fly.registerTower(tower);

			runSimulation(tower);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
