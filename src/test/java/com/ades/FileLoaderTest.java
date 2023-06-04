package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import com.ades.InvalidDataException;
import com.ades.FileLoader;
import com.ades.Location;
import com.ades.Airplane;
import com.ades.Weather;

public class FileLoaderTest {

    @Test
    public void testLoadLocationsFromFile() {
        FileLoader fileLoader = new FileLoader();
        String fileName = "src/data/locations.csv";

        try {
            List<Location> locations = fileLoader.loadLocationsFromFile(fileName);

            Assertions.assertEquals(3, locations.size());

            Location location1 = locations.get(0);
            Assertions.assertEquals("City1", location1.getName());
            Assertions.assertEquals(40.0, location1.getLatitude());
            Assertions.assertEquals(50.0, location1.getLongitude());

            // Add assertions for the remaining locations

        } catch (IOException | InvalidDataException e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testLoadAirplanesFromFile() throws InvalidDataException {
        FileLoader fileLoader = new FileLoader();
        String fileName = "src/data/airplanes.csv";

        try {
            List<Airplane> airplanes = fileLoader.loadAirplanesFromFile(fileName);

            Assertions.assertEquals(2, airplanes.size());

            Airplane airplane1 = airplanes.get(0);
            Assertions.assertEquals("Boeing 747", airplane1.getName());
            Assertions.assertEquals(1000.0, airplane1.getRange());
            Assertions.assertEquals(5000.0, airplane1.getFuelCapacity());
            Assertions.assertEquals(100.0, airplane1.getFuelBurnRate());

            // Add assertions for the remaining airplanes

        } catch (IOException e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testLoadWeatherFromFile() {
        FileLoader fileLoader = new FileLoader();
        String fileName = "src/data/weather.csv";

        try {
            List<Weather<String>> weatherList = fileLoader.loadWeatherFromFile(fileName, String::valueOf);

            Assertions.assertEquals(3, weatherList.size());

            Weather<String> weather1 = weatherList.get(0);
            Assertions.assertEquals("Spring", weather1.getSeason());
            Assertions.assertEquals(10.0, weather1.getWindSpeed());
            Assertions.assertEquals(25.0, weather1.getTemperature());
            Assertions.assertEquals(60.0, weather1.getHumidity());

            // Add assertions for the remaining weather data

        } catch (IOException | InvalidDataException e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }
}
