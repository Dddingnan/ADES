package com.ades;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws InvalidDataException, InterruptedException, ExecutionException {

        Connection conn = null;
        try {
            // Database.createNewDatabase();
            conn = Database.connect();
            // Database.createNewTable();
            // Database.insert("airplanes", "Boeing 747", 20000, 5, 900);
            // Database.selectAll("airplanes");
            // Database.update("airplanes", "Airbus A380", 30000, 1);
            Database.selectAll("airplanes");
        } finally {
            Database.close(conn);
        }

        // System.out.println("Hello! Welcome to the Airplane Destination Evaluation
        // System!");
        // System.out.println("--------------------------------------------------------");
        // List<Location> locations = new ArrayList<>();
        // List<Airplane> airplanes = new ArrayList<>();
        // FileLoader loader = new FileLoader();
        // try {
        // Season currentSeason = SeasonUtils.getCurrentSeason();
        // System.out.println("User current season: " + currentSeason);
        // System.out.println("--------------------------------------------------------");
        // locations = loader.loadLocationsFromFile("src/data/locations.csv");
        // airplanes = loader.loadAirplanesFromFile("src/data/airplanes.csv");
        // List<Weather<Season>> weatherList =
        // loader.loadWeatherFromFile("/data/weather.json");
        // // Get the user's current data and determine the season
        // Weather<Season> currentWeather = weatherList.stream()
        // .filter(weather -> weather.getSeason() == currentSeason)
        // .findFirst()
        // .orElse(null);
        // TravelCalculator travelCalculator = new TravelCalculator(locations,
        // currentWeather);
        // UserInterface ui = new UserInterface(locations, airplanes, travelCalculator);
        // ui.start();
        // } catch (FileNotFoundException e) {
        // System.out.println("File not found. Please check the file name and try
        // again.");
        // e.printStackTrace();
        // } catch (IOException e) {
        // System.out.println("Error occurred while reading the file.");
        // e.printStackTrace();
        // }
    }
}