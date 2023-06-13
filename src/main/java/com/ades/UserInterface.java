package com.ades;

import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

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

    public void start() throws InterruptedException, ExecutionException {
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

            Map<Location, FlightData> reachableLocations = travelCalculator.calculateReachableLocations(airplane,
                    currentLocation);
            System.out.println("Potential city destinations on a single tank of fuel:");
            int index = 1;
            for (Map.Entry<Location, FlightData> entry : reachableLocations.entrySet()) {
                Location location = entry.getKey();
                FlightData flightData = entry.getValue();
                Double duration = flightData.getDuration();
                Double fuelConsumption = flightData.getFuelConsumption();
                Double CO2Emissions = flightData.getFutureCO2Emissions().get(); // Here we retrieve the result
                Double flightCost = flightData.getFutureFlightCost().get(); // Here we retrieve the result

                System.out.println("\n" + index++ + ". Destination: " + location.getName());
                System.out.println("   Estimated flight duration: " + String.format("%.2f", duration) + " hours");
                System.out.println("   Fuel consumption: " + String.format("%.2f", fuelConsumption) + " gallons");
                System.out.println("   CO2 Emissions: " + String.format("%.2f", CO2Emissions) + " kg");
                System.out.println("   Estimated Flight Cost: $" + String.format("%.2f", flightCost));
            }
            // TODO
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            String json = "";
            try {
                json = writer.writeValueAsString(reachableLocations);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
            try (FileWriter fileWriter = new FileWriter("reachable_locations.json")) {
                fileWriter.write(json);
            } catch (IOException e) {
                System.out.println(e.getMessage());
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