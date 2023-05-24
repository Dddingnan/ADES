package test.java.com.ades;

import org.junit.jupiter.api.Test;

import main.java.com.ades.InvalidDataException;
import main.java.com.ades.Airplane;
import main.java.com.ades.Boeing747;
import main.java.com.ades.AirbusA380;
import static org.junit.jupiter.api.Assertions.*;

public class AirplaneTest {

    @Test
    public void testBoeing747FuelConsumption() throws InvalidDataException {
        // Test the fuel consumption calculation for Boeing747
        String name = "Boeing747";
        double range = 1000.0;
        double fuelCapacity = 5000.0;
        double fuelBurnRate = 50.0;

        Airplane boeing747 = new Boeing747(name, range, fuelCapacity, fuelBurnRate);

        double expectedFuelConsumption = range / (fuelCapacity / (fuelBurnRate * 0.95));
        assertEquals(expectedFuelConsumption, boeing747.getFuelConsumption());
    }

    @Test
    public void testAirbusA380FuelConsumption() throws InvalidDataException {
        // Test the fuel consumption calculation for AirbusA380
        String name = "AirbusA380";
        double range = 1000.0;
        double fuelCapacity = 5000.0;
        double fuelBurnRate = 50.0;

        Airplane airbusA380 = new AirbusA380(name, range, fuelCapacity, fuelBurnRate);

        double expectedFuelConsumption = (range * 1.1) / (fuelCapacity / fuelBurnRate);
        assertEquals(expectedFuelConsumption, airbusA380.getFuelConsumption());
    }
}
