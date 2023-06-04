package com.ades;

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
                    String input = scanner.next();
                    if (!input.matches("\\d+")) { // Check if input not an integer number
                        throw new InvalidDataException("Invalid data input. Please enter an integer number.",
                                "UserInterface - Location - Not integer", input);
                    }

                    locationIndex = Integer.parseInt(input) - 1;

                    if (locationIndex < 0 || locationIndex >= locations.size()) { // Check if input is within the valid
                                                                                  // range
                        throw new InvalidDataException(
                                "Invalid input range. Please enter a number between 1 and " + locations.size(),
                                "UserInterface - Not within the valid range", input);
                    }

                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine(); // discard the invalid input
                    locationIndex = -1; // Reset the index, forcing the loop to repeat
                    continue;
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
                    String input = scanner.next();
                    if (!input.matches("\\d+")) { // Check if input not an integer number
                        throw new InvalidDataException("Invalid data input. Please enter an integer number.",
                                "UserInterface - Airplane - Not integer", input);
                    }

                    airplaneIndex = Integer.parseInt(input) - 1;

                    if (airplaneIndex < 0 || airplaneIndex >= locations.size()) { // Check if input is within the valid
                                                                                  // range
                        throw new InvalidDataException(
                                "Invalid input range. Please enter a number between 1 and " + airplanes.size(),
                                "UserInterface - Not within the valid range", input);
                    }

                } catch (InvalidDataException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine(); // discard the invalid input
                    airplaneIndex = -1; // Reset the index, forcing the loop to repeat
                    continue;
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
