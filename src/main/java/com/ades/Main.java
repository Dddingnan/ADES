package main.java.com.ades;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello! Welcome to the Airplane Destination Evaluation System!");
        System.out.println("--------------------------------------------------------");
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Boston", 42.3601, -71.0589));
        locations.add(new Location("New York", 40.7128, -74.0060));
        locations.add(new Location("Los Angeles", 34.0522, -118.2437));
        locations.add(new Location("Taiwan", 23.6978, 120.9605));
        locations.add(new Location("Tokyo", 35.6895, 139.6917));
        locations.add(new Location("Shanghai", 31.2304, 121.4737));
        locations.add(new Location("Bangkok", 13.7563, 100.5018));
        locations.add(new Location("Singapore", 1.3521, 103.8198));
        locations.add(new Location("Ho Chi Minh City", 10.8231, 106.6297));
        locations.add(new Location("Paris", 48.8566, 2.3522));
        locations.add(new Location("Berlin", 52.5200, 13.4050));
        locations.add(new Location("Madrid", 40.4168, -3.7038));
        locations.add(new Location("Rome", 41.9028, 12.4964));
        locations.add(new Location("London", 51.5074, -0.1278));
        locations.add(new Location("Cairo", 30.0444, 31.2357));
        locations.add(new Location("Johannesburg", -26.2041, 28.0473));
        locations.add(new Location("Nairobi", -1.286389, 36.817223));
        
        List<Airplane> airplanes = new ArrayList<>();
        // Upcasting
        airplanes.add(new Boeing747("Boeing 747", 8535, 63360, 3200));
        airplanes.add(new AirbusA380("Airbus A380", 8000, 81900, 3300));
        
        TravelCalculator travelCalculator = new TravelCalculator(locations);
        
        UserInterface ui = new UserInterface(locations, airplanes, travelCalculator);
        ui.start();
    }
}