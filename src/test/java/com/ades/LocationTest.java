package test.java.com.ades;

import org.junit.jupiter.api.Test;
import com.ades.Location;
import com.ades.InvalidDataException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    public void testValidLocation() {
        // Test creating a valid location
        String name = "City";
        double latitude = 50.0;
        double longitude = 100.0;

        assertDoesNotThrow(() -> new Location(name, latitude, longitude));
    }

    @Test
    public void testInvalidLatitude() {
        // Test creating a location with an invalid latitude
        String name = "City";
        double latitude = -100.0;
        double longitude = 100.0;

        assertThrows(InvalidDataException.class, () -> new Location(name, latitude, longitude));
    }

    @Test
    public void testInvalidLongitude() {
        // Test creating a location with an invalid longitude
        String name = "City";
        double latitude = 50.0;
        double longitude = 200.0;

        assertThrows(InvalidDataException.class, () -> new Location(name, latitude, longitude));
    }

    @Test
    public void testValidGetters() {
        // Test getters of a valid location
        String name = "City";
        double latitude = 50.0;
        double longitude = 100.0;

        Location location = assertDoesNotThrow(() -> new Location(name, latitude, longitude));

        assertEquals(name, location.getName());
        assertEquals(latitude, location.getLatitude());
        assertEquals(longitude, location.getLongitude());
    }
}
