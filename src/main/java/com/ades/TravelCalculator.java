package main.java.com.ades;

import java.util.List;
import java.util.ArrayList;

public class TravelCalculator {
    private List<Location> locations;

    public TravelCalculator(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> calculateReachableLocations(Airplane airplane, Location currentLocation) {
        List<Location> reachableLocations = new ArrayList<>();
        for (Location location : locations) {
            if (!location.equals(currentLocation)) {
                double distance = calculateDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                                                    location.getLatitude(), location.getLongitude());
                if (distance <= airplane.getRange()) {
                    reachableLocations.add(location);
                }
            }
        }
        return reachableLocations;
    }
    
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    	// Haversine formula
    	// https://en.wikipedia.org/wiki/Haversine_formula
        int earthRadius = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                    Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadius * c; // Distance in km
    }
    
}
