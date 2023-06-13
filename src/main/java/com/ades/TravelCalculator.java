package com.ades;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TravelCalculator {
    private List<Location> locations;
    private Weather<Season> weather;

    public TravelCalculator(List<Location> locations, Weather<Season> weather) {
        this.locations = locations;
        this.weather = weather;
    }

    public Map<Location, FlightData> calculateReachableLocations(Airplane airplane,
            Location currentLocation) {
        Map<Location, FlightData> reachableLocations = new HashMap<>();
        // Concurrency
        // Get the number of available processor cores
        int processors = Runtime.getRuntime().availableProcessors();
        // Create a thread pool with a number of threads equal to half of the available
        // processor cores
        ExecutorService executorService = Executors.newFixedThreadPool(processors / 2);

        for (Location location : locations) {
            if (!location.equals(currentLocation)) {
                double distance = calculateDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                        location.getLatitude(), location.getLongitude());
                double weatherFactor = calculateWeatherFactor(weather);
                double finalAirplaneRange = airplane.getRange() * weatherFactor;
                if (distance <= finalAirplaneRange) {
                    double duration = calculateFlightDuration(airplane, distance);
                    double fuelConsumption = calculateFuelConsumption(airplane, distance, weatherFactor);
                    // Create a new task to calculate CO2 emissions, submit it to the executor
                    // service and
                    // store the reference to the Future object. The Future object will be used to
                    // retrieve
                    // the result of the calculation when it is ready.
                    Future<Double> futureCO2Emissions = executorService
                            .submit(new CO2EmissionsCalculator(fuelConsumption));
                    // Create a new task to calculate the flight cost, submit it to the executor
                    // service and
                    // store the reference to the Future object. The Future object will be used to
                    // retrieve
                    // the result of the calculation when it is ready.
                    Future<Double> futureFlightCost = executorService
                            .submit(new FlightCostCalculator(fuelConsumption));

                    FlightData flightData = new FlightData(duration, fuelConsumption, futureCO2Emissions,
                            futureFlightCost);
                    reachableLocations.put(location, flightData);
                }
            }
        }
        // Shutdown the executor service. No new tasks will be accepted. This does not
        // interrupt previously submitted tasks and they will continue to run to
        // completion.
        executorService.shutdown();
        return reachableLocations;
    }

    public double calculateWeatherFactor(Weather<Season> weather) {
        // Pre-condition: The weather object must not be null
        assert weather != null : "Weather object cannot be null";

        double weatherFactor = 1.0;

        Season season = weather.getSeason();
        double windSpeed = weather.getWindSpeed();
        double temperature = weather.getTemperature();
        double humidity = weather.getHumidity();
        // Adjust the weather factor based on the weather indices and season
        if (season == Season.Spring) {
            // Adjust the weather factor for Spring season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 50 && temperature > 25 && humidity < 60) {
                weatherFactor *= 0.8; // Reduce the weather factor by 20%
            } else if (windSpeed < 10 && temperature < 15 && humidity > 70) {
                weatherFactor *= 1.2; // Increase the weather factor by 20%
            }
        } else if (season == Season.Summer) {
            // Adjust the weather factor for Summer season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 40 && temperature > 30 && humidity < 50) {
                weatherFactor *= 0.7; // Reduce the weather factor by 30%
            } else if (windSpeed < 20 && temperature < 25 && humidity > 60) {
                weatherFactor *= 1.1; // Increase the weather factor by 10%
            }
        } else if (season == Season.Autumn) {
            // Adjust the weather factor for Autumn season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 30 && temperature > 20 && humidity < 55) {
                weatherFactor *= 0.9; // Reduce the weather factor by 10%
            } else if (windSpeed < 15 && temperature < 20 && humidity > 65) {
                weatherFactor *= 1.05; // Increase the weather factor by 5%
            }
        } else if (season == Season.Winter) {
            // Adjust the weather factor for Winter season based on wind speed, temperature,
            // and humidity
            if (windSpeed > 60 && temperature < 5 && humidity < 40) {
                weatherFactor *= 0.6; // Reduce the weather factor by 40%
            } else if (windSpeed < 30 && temperature > -5 && humidity > 50) {
                weatherFactor *= 1.3; // Increase the weather factor by 30%
            }
        }

        // Post-condition: The calculated weather factor must be a valid value between 0
        // and 2
        assert weatherFactor >= 0 && weatherFactor <= 2 : "Invalid weather factor";

        // Return the calculated weather factor
        return weatherFactor;
    }

    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        // Haversine formula
        // https://en.wikipedia.org/wiki/Haversine_formula
        int earthRadius = 6371; // Radius of the earth in km

        double latDifference = Math.toRadians(latitude2 - latitude1);
        double lonDifference = Math.toRadians(longitude2 - longitude1);

        double lat1Radians = Math.toRadians(latitude1);
        double lat2Radians = Math.toRadians(latitude2);

        double sinLatDifferenceSquared = Math.sin(latDifference / 2) * Math.sin(latDifference / 2);
        double sinLonDifferenceSquared = Math.sin(lonDifference / 2) * Math.sin(lonDifference / 2);

        double haversineA = sinLatDifferenceSquared
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * sinLonDifferenceSquared;
        double haversineC = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1 - haversineA));

        return earthRadius * haversineC; // Distance in km
    }

    public double calculateFlightDuration(Airplane airplane, double distance) {
        double speed = airplane.getSpeed();
        return distance / speed; // Time in hours
    }

    public double calculateFuelConsumption(Airplane airplane, double distance, double weatherFactor) {
        return distance / airplane.getFuelConsumption() * weatherFactor;
    }

}
