package test.java.com.ades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.java.com.ades.Weather;

public class WeatherTest {

    @Test
    public void testGetSeason() {
        Weather<String> weather = new Weather<>("Spring", 10.0, 25.0, 60.0);

        String season = weather.getSeason();

        Assertions.assertEquals("Spring", season);
    }

    @Test
    public void testSetSeason() {
        Weather<String> weather = new Weather<>("Summer", 15.0, 30.0, 70.0);

        weather.setSeason("Autumn");

        String season = weather.getSeason();
        Assertions.assertEquals("Autumn", season);
    }

    @Test
    public void testGetWindSpeed() {
        Weather<String> weather = new Weather<>("Winter", 20.0, 5.0, 40.0);

        double windSpeed = weather.getWindSpeed();

        Assertions.assertEquals(20.0, windSpeed);
    }

    @Test
    public void testSetWindSpeed() {
        Weather<String> weather = new Weather<>("Spring", 10.0, 25.0, 60.0);

        weather.setWindSpeed(12.5);

        double windSpeed = weather.getWindSpeed();
        Assertions.assertEquals(12.5, windSpeed);
    }

    @Test
    public void testGetTemperature() {
        Weather<String> weather = new Weather<>("Summer", 15.0, 30.0, 70.0);

        double temperature = weather.getTemperature();

        Assertions.assertEquals(30.0, temperature);
    }

    @Test
    public void testSetTemperature() {
        Weather<String> weather = new Weather<>("Autumn", 10.0, 20.0, 50.0);

        weather.setTemperature(18.5);

        double temperature = weather.getTemperature();
        Assertions.assertEquals(18.5, temperature);
    }

    @Test
    public void testGetHumidity() {
        Weather<String> weather = new Weather<>("Winter", 20.0, 5.0, 40.0);

        double humidity = weather.getHumidity();

        Assertions.assertEquals(40.0, humidity);
    }

    @Test
    public void testSetHumidity() {
        Weather<String> weather = new Weather<>("Spring", 10.0, 25.0, 60.0);

        weather.setHumidity(55.0);

        double humidity = weather.getHumidity();
        Assertions.assertEquals(55.0, humidity);
    }
}
