package test.java.com.ades;


import main.java.com.ades.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO
public class UserInterfaceTest {

    private UserInterface userInterface;

    @BeforeEach
    public void setUp() {
        // set up the test data
        List<Location> locations = Arrays.asList(
                new Location("New York", 40.712776, -74.005974),
                new Location("Los Angeles", 34.052235, -118.243683),
                new Location("Boston", 42.360081, -71.058884)
        );

        List<Airplane> airplanes = Arrays.asList(
                new Boeing747("Boeing 747", 8535, 63360, 3200),
                new AirbusA380("Airbus A380", 8000, 85300, 3000)
        );

        TravelCalculator travelCalculator = new TravelCalculator(locations);

        // instantiate the UserInterface
        this.userInterface = new UserInterface(locations, airplanes, travelCalculator);
    }

    @Test
    public void testName() {
        // test case here
        // assertEquals(expected, actual);
    }
}
