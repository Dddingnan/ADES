package main.java.com.ades;

public abstract class AbstractAirplane implements Airplane {
    protected String name;
    protected double range;
    protected double fuelCapacity; // in gallons
    protected double fuelBurnRate; // in gallons per hour

    public AbstractAirplane(String name, double range, double fuelCapacity, double fuelBurnRate) {
        this.name = name;
        this.range = range;
        this.fuelCapacity = fuelCapacity;
        this.fuelBurnRate = fuelBurnRate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public double getFuelCapacity() {
        return this.fuelCapacity;
    }
    
    @Override
    public double getFuelBurnRate() {
        return this.fuelBurnRate;
    }

}
