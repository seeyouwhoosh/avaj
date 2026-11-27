package avaj;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

import avaj.exception.*;


public class Main {
	private static int simulationRuns = 0;

	private static void createAircraft(Scanner sc, ArrayList<Flyable> flyables) {
		String type;
		String name;
		int longitude;
		int latitude;
		int height;
	
		try {
			if (!sc.hasNext())
				throw new InvalidFormatException("AIRCRAFT_TYPE", String.class.getSimpleName());
			type = sc.next();

			if (!sc.hasNext())
				throw new InvalidFormatException("NAME", String.class.getSimpleName());
			name = sc.next();

			if (!sc.hasNextInt())
				throw new InvalidFormatException("LONGITUDE", Integer.class.getSimpleName());
			longitude = sc.nextInt();

			if (!sc.hasNextInt())
				throw new InvalidFormatException("LATITUDE", Integer.class.getSimpleName());
			latitude = sc.nextInt();

			if (!sc.hasNextInt())
				throw new InvalidFormatException("HEIGHT", Integer.class.getSimpleName());
			height = sc.nextInt();
			if (height > 100)
				height = 100;

			if (sc.hasNextLine())
				throw new InvalidTokenException();

			Flyable fly = AircraftFactory.getInstance().newAircraft(type, name, new Coordinates(longitude, latitude, height));

			flyables.add(fly);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(1);
		}
	}

	public static void error(String msg) {
		System.out.println(msg);
		System.exit(1);
	}

	private static ArrayList<Flyable> loadAircrafts(String[] args) {
		File file = new File(args[0]);
		if (!file.exists() || !file.isFile() || !file.canRead() || file.length() == 0)
			error("Invalid file");
	
		try (Scanner scanner = new Scanner(file)) {
			if (!scanner.hasNextInt())
				throw new InvalidTokenException();

			simulationRuns = scanner.nextInt();
			if (simulationRuns <= 0)
				throw new NegativeNumberException("simulationRuns");

			String line = scanner.nextLine();
			if (!line.isEmpty())
				error("First line shouldn\'t contain more than the number of simulation runs");

			ArrayList<Flyable> flyables = new ArrayList<>();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				try (Scanner tokensScanner = new Scanner(line)) {
					createAircraft(tokensScanner, flyables);
				} catch (Exception e) {
					e.printStackTrace(System.out);
					System.exit(1);
				}
			}
			return flyables;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(1);
		}
		return null;
	}

	private static void runSimulation(WeatherTower tower) {
		for (long i = 0; i < simulationRuns; i++)
			tower.conditionChanged();
	}

	public static void main(String[] args) {
		if (args.length != 1)
			error("Invalid arguments");

		ArrayList<Flyable> flyables = loadAircrafts(args);

		try (FileWriter writer = new FileWriter("simulation.txt", false)){
			WeatherTower tower = new WeatherTower();
			tower.setLogReporter(writer);

			for (Flyable fly : flyables)
				fly.registerTower(tower);

			runSimulation(tower);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.exit(1);
		}
	}
}
