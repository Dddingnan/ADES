package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ades.*;

public class TravelCalculatorTest {
    private List<Location> locations;
    private Weather<Season> weather;

    @BeforeEach
    public void setUp() throws InvalidDataException {
        // Create sample locations
        locations = new ArrayList<>();
        locations.add(new Location("City1", 40.7128, -74.0060));
        locations.add(new Location("City2", 34.0522, -118.2437));
        locations.add(new Location("City3", 51.5074, -0.1278));

        // Create a sample weather object
        weather = new Weather<>(Season.Spring, 10.0, 20.0, 70.0);
    }

    @Test
    public void testCalculateReachableLocations() throws InvalidDataException {
        // Create a sample airplane
        Airplane airplane = new Boeing747("Boeing 747", 10000, 50000, 1000, 500);

        // Create a sample current location
        Location currentLocation = new Location("CurrentCity", 40.7128, -74.0060);

        // Create an instance of TravelCalculator
        TravelCalculator travelCalculator = new TravelCalculator(locations, weather);

        // Calculate reachable locations
        Map<Location, Double> reachableLocations = travelCalculator.calculateReachableLocations(airplane,
                currentLocation);

        // Assert that reachableLocations contains the expected locations
        Assertions.assertTrue(reachableLocations.containsKey(locations.get(0))); // City1 is reachable
        Assertions.assertFalse(reachableLocations.containsKey(locations.get(1))); // City2 is not reachable
        Assertions.assertFalse(reachableLocations.containsKey(locations.get(2))); // City3 is not reachable
    }

    @Test
    public void testCalculateWeatherFactor() {
        // Create an instance of TravelCalculator
        TravelCalculator travelCalculator = new TravelCalculator(locations, weather);

        // Calculate the weather factor
        double weatherFactor = travelCalculator.calculateWeatherFactor(weather);

        // Assert that the weather factor is within the valid range
        Assertions.assertTrue(weatherFactor >= 0 && weatherFactor <= 2);
    }

    @Test
    public void testCalculateDistance() {
        // Create an instance of TravelCalculator
        TravelCalculator travelCalculator = new TravelCalculator(locations, weather);

        // Calculate the distance between two locations
        double distance = travelCalculator.calculateDistance(40.7128, -74.0060, 34.0522, -118.2437);

        // Assert that the distance is within a reasonable range
        Assertions.assertTrue(distance > 4000 && distance < 5000); // Approximate distance between City1 and City2
    }
}
