package main.java.com.ades;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private List<Location> locations;
    private List<Airplane> airplanes;
    private TravelCalculator travelCalculator;
    private Scanner scanner;

    public UserInterface(List<Location> locations, List<Airplane> airplanes, TravelCalculator travelCalculator) {
        this.locations = locations;
        this.airplanes = airplanes;
        this.travelCalculator = travelCalculator;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Please select your current location:");
            for (int i = 0; i < locations.size(); i++) {
                System.out.println((i + 1) + ". " + locations.get(i).getName());
            }

            int locationIndex = -1;
            while (locationIndex < 0 || locationIndex >= locations.size()) {
                System.out.print("Enter a number between 1 and " + locations.size() + ": ");
                try {
                    locationIndex = scanner.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // discard the invalid input
                    continue;
                }
                if (locationIndex < 0 || locationIndex >= locations.size()) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            Location currentLocation = locations.get(locationIndex);

            System.out.println("Please select your airplane type:");
            for (int i = 0; i < airplanes.size(); i++) {
                System.out.println((i + 1) + ". " + airplanes.get(i).getName());
            }

            int airplaneIndex = -1;
            while (airplaneIndex < 0 || airplaneIndex >= airplanes.size()) {
                System.out.print("Enter a number between 1 and " + airplanes.size() + ": ");
                try {
                    airplaneIndex = scanner.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // discard the invalid input
                    continue;
                }
                if (airplaneIndex < 0 || airplaneIndex >= airplanes.size()) {
                    System.out.println("Invalid input. Please try again.");
                }
            }
            Airplane airplane = airplanes.get(airplaneIndex);

            List<Location> reachableLocations = travelCalculator.calculateReachableLocations(airplane, currentLocation);
            System.out.println("Potential city destinations on a single tank of fuel:");
            for (int i = 0; i < reachableLocations.size(); i++) {
                System.out.println((i + 1) + ". " + reachableLocations.get(i).getName());
            }
            
            System.out.println("--------------------------------------------------------");
            System.out.println("Do you want to make another calculation? (yes/no)");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("no")) {
            	System.out.println("See you next time~!");
                break;
            }
        }
        scanner.close();
    }
}
