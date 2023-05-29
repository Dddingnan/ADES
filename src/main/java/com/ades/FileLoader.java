package main.java.com.ades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileLoader {
	public String line = "";
	public String splitBy = ",";
    /*
     * Method: loadLocationsFromFile
     * Precondition: The file with the given fileName must exist and be readable.
     * 				 The file should be in CSV format with each line representing one location,
     * 				 and each line should be in the format: "<city_name>,<latitude>,<longitude>".
     * Postcondition: Returns a list of Location objects loaded from the file.
     * 				  Each Location object corresponds to one line in the file.
     * Throws: FileNotFoundException if the file with the given fileName does not
     * 		   exist.
     * 		   IOException if an error occurs while reading the file.
     */
    public List<Location> loadLocationsFromFile(String fileName) throws FileNotFoundException, IOException, InvalidDataException {
        List<Location> locations = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] locationData = line.split(splitBy); // Split the line by commas
            String name = locationData[0];
            double latitude = Double.parseDouble(locationData[1]);
            double longitude = Double.parseDouble(locationData[2]);
            try {
            	locations.add(new Location(name, latitude, longitude));
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();

        return locations;
    }
    
    /*
     * Method: loadAirplanesFromFile
     * Precondition: The file with the given fileName must exist and be readable. 
     *               The file should be in CSV format with each line representing one airplane, 
     *               and each line should be in the format: "<airplane_name>,<range>,<fuelCapacity>,<fuelBurnRate>".
     * Postcondition: Returns a list of Airplane objects loaded from the file. 
     *                Each Airplane object corresponds to one line in the file.
     * Throws: FileNotFoundException if the file with the given fileName does not exist.
     *         IOException if an error occurs while reading the file.
     */
    public List<Airplane> loadAirplanesFromFile(String fileName) throws FileNotFoundException, IOException {
        List<Airplane> airplanes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] airplaneData = line.split(splitBy);
            String name = airplaneData[0];
            double range = Double.parseDouble(airplaneData[1]);
            double fuelCapacity = Double.parseDouble(airplaneData[2]);
            double fuelBurnRate = Double.parseDouble(airplaneData[3]);
            try {
                if (name.equals("Boeing 747")) {
    			    airplanes.add(new Boeing747(name, range, fuelCapacity, fuelBurnRate));
    			} else if (name.equals("Airbus A380")) {
    			    airplanes.add(new AirbusA380(name, range, fuelCapacity, fuelBurnRate));
    			} else {
    			    System.out.println("Unknown airplane type: " + name);
    			}
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }
        br.close();

        return airplanes;
    }
    
    /*
     * Method: loadWeatherFromFile
     * Precondition: The file with the given fileName must exist and be readable.
     *               The file should be in CSV format with each line representing one weather data,
     *               and each line should be in the format: "<season>,<windSpeed>,<temperature>,<humidity>".
     * Postcondition: Returns a list of Weather objects loaded from the file.
     *                Each Weather object corresponds to one line in the file.
     * Throws: FileNotFoundException if the file with the given fileName does not exist.
     *         IOException if an error occurs while reading the file.
     */
    public <T> List<Weather<T>> loadWeatherFromFile(String fileName, Function<String, T> seasonParser) throws FileNotFoundException, IOException, InvalidDataException {
        List<Weather<T>> weatherList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            String[] weatherData = line.split(splitBy);
            T season = seasonParser.apply(weatherData[0]);
            double windSpeed = Double.parseDouble(weatherData[1]);
            double temperature = Double.parseDouble(weatherData[2]);
            double humidity = Double.parseDouble(weatherData[3]);
            Weather<T> weather = new Weather<>(season, windSpeed, temperature, humidity);
            weatherList.add(weather);
        }
        br.close();
        return weatherList;
    }
}
