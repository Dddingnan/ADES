package com.ades;

import java.util.concurrent.Callable;

public class FlightCostCalculator implements Callable<Double> {
    private final double fuelConsumption;
    private static final double FUEL_COST_PER_GALLON = 4.15;

    public FlightCostCalculator(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public Double call() {
        return fuelConsumption * FUEL_COST_PER_GALLON;
    }
}
