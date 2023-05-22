package main.java.com.ades;

public class Boeing747 extends AbstractAirplane {
    public Boeing747(String name, double range, double fuelCapacity, double fuelBurnRate) throws InvalidDataException {
        super(name, range, fuelCapacity, fuelBurnRate);
    }

    @Override
    public double getFuelConsumption() {
    	// Polymorphism
        // Boeing747 has a special engine technology that allows it to save 5% of fuel per hour of flight
        double adjustedFuelBurnRate = this.getFuelBurnRate() * 0.95;
        double hoursOfFlight = this.getFuelCapacity() / adjustedFuelBurnRate;
        return this.getRange() / hoursOfFlight;
    }
}
