package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.com.ades.*;

import java.util.ArrayList;
import java.util.List;

public class TravelCalculatorTest {
    private TravelCalculator travelCalculator;
    private List<Location> locations;

    @BeforeEach
    public void setup() throws InvalidDataException {
        // Initialize the test data
        locations = new ArrayList<>();
        Location location1 = new Location("City1", 40.7128, -74.0060);
        Location location2 = new Location("City2", 34.0522, -118.2437);
        Location location3 = new Location("City3", 51.5074, -0.1278);
        locations.add(location1);
        locations.add(location2);
        locations.add(location3);

        travelCalculator = new TravelCalculator(locations);
    }

    @Test
    public void testCalculateReachableLocations() {
        // Create a mock airplane
        Airplane airplane = new Airplane() {
            @Override
            public String getName() {
                return "TestAirplane";
            }

            @Override
            public double getRange() {
                return 2000.0;
            }

            @Override
            public double getFuelCapacity() {
                return 1000.0;
            }

            @Override
            public double getFuelBurnRate() {
                return 100.0;
            }

            @Override
            public double getFuelConsumption() {
                return 10.0;
            }
        };

        // Set the current location
        Location currentLocation = locations.get(0);

        // Call the method being tested
        List<Location> reachableLocations = travelCalculator.calculateReachableLocations(airplane, currentLocation);

        // Assert that the reachable locations are correct
        Assertions.assertEquals(2, reachableLocations.size());
        Assertions.assertTrue(reachableLocations.contains(locations.get(1)));
        Assertions.assertTrue(reachableLocations.contains(locations.get(2)));
    }

    @Test
    public void testCalculateDistance() {
        double distance = travelCalculator.calculateDistance(40.7128, -74.0060, 34.0522, -118.2437);
        Assertions.assertEquals(3939.2, distance, 0.1); // Accept a small tolerance for floating-point calculations
    }
}
