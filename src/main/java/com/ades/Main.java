package main.java.com.ades;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InvalidDataException {
        System.out.println("Hello! Welcome to the Airplane Destination Evaluation System!");
        System.out.println("--------------------------------------------------------");
        // Weather<Season> weather = new Weather<>(Season.Summer, 10.5, 25.0, 60.0);
        // // Access the weather indices
        // Season season = weather.getSeason();
        // // Output the results
        // System.out.println("Season: " + season);

        // Weather<String> weather1 = new Weather<String>("Winter", 8, 21.0, 100.0);
        // // Access the weather indices
        // String season1 = weather1.getSeason();
        // // Output the results
        // System.out.println("Season: " + season1);
        List<Location> locations = new ArrayList<>();
        List<Airplane> airplanes = new ArrayList<>();
        FileLoader loader = new FileLoader();
        try {
            locations = loader.loadLocationsFromFile("src/data/locations.csv");
            airplanes = loader.loadAirplanesFromFile("src/data/airplanes.csv");
            TravelCalculator travelCalculator = new TravelCalculator(locations);
            UserInterface ui = new UserInterface(locations, airplanes, travelCalculator);
            ui.start();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check the file name and try again.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}