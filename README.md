# Assignment 3

## About The Project

```
This project is a sophisticated tool for airplane owners to plan travels. By entering their location and airplane type, users can find destinations within their non-refueling range. The tool determines flight range, potential destinations, and travel duration, considering aircraft specifics and fuel status. It also estimates fuel usage and CO2 emissions with respect to aircraft efficiency, flight distance, and weather conditions. Moreover, it calculates potential fuel costs. New features include an expanded list of airplane types for precise calculations and database integration for enhanced data management, effectively replacing file-based data loading. It handles database issues seamlessly. The tool gives users a comprehensive overview of each destination, promoting informed, eco-friendly travel decisions
```

---

## Built With

- [Java](<https://en.wikipedia.org/wiki/Java_(programming_language)>)

---

## Getting Started

### Prerequisites

- Java 17
- Eclipse
- Maven

### Installation

- [Here](https://www.java.com/en/download/help/download_options.html)

### How to run?

#### Eclipse

- Start Eclipse
- Go to File-> Import-> General-> Existing Projects Into Workspace
- Select the archive file option
- browse to the ADES zip file in this folder
- click Finish

#### Maven

- Without Test:

  1. mvn clean install -DskipTests
  2. mvn exec:java

- With Test:
  1. mvn clean install
  2. mvn exec:java

---

## Expected result

```
Hello! Welcome to the Airplane Destination Evaluation System!
--------------------------------------------------------
User current season: Summer
--------------------------------------------------------
Please select your current location:
1. Boston
2. New York
3. Los Angeles
4. Taiwan
5. Tokyo
6. Shanghai
7. Bangkok
8. Singapore
9. Ho Chi Minh City
10. Paris
11. Berlin
12. Madrid
13. Rome
14. London
15. Cairo
16. Johannesburg
17. Nairobi
Enter a number between 1 and 17: 4
Please select your airplane type:
1. Boeing 747
2. Airbus A380
Enter a number between 1 and 2: 1
Potential city destinations on a single tank of fuel:

1. Destination: Bangkok
   Estimated flight duration: 4.03 hours
   Fuel consumption: 6.49 gallons
   CO2 Emissions: 65.58 kg
   Estimated Flight Cost: $26.95

2. Destination: Ho Chi Minh City
   Estimated flight duration: 3.48 hours
   Fuel consumption: 5.60 gallons
   CO2 Emissions: 56.59 kg
   Estimated Flight Cost: $23.25

3. Destination: Cairo
   Estimated flight duration: 14.49 hours
   Fuel consumption: 23.35 gallons
   CO2 Emissions: 235.81 kg
   Estimated Flight Cost: $96.89

4. Destination: Berlin
   Estimated flight duration: 15.07 hours
   Fuel consumption: 24.29 gallons
   CO2 Emissions: 245.33 kg
   Estimated Flight Cost: $100.80

5. Destination: Tokyo
   Estimated flight duration: 3.73 hours
   Fuel consumption: 6.02 gallons
   CO2 Emissions: 60.79 kg
   Estimated Flight Cost: $24.98

6. Destination: Singapore
   Estimated flight duration: 5.16 hours
   Fuel consumption: 8.32 gallons
   CO2 Emissions: 83.99 kg
   Estimated Flight Cost: $34.51

7. Destination: Shanghai
   Estimated flight duration: 1.40 hours
   Fuel consumption: 2.25 gallons
   CO2 Emissions: 22.77 kg
   Estimated Flight Cost: $9.35
--------------------------------------------------------
Do you want to make another calculation? (yes/no)
no
See you next time~!
```

---

## Requirements

- 2.1 Menu Interface
  - The application shall provide a user interface for the user to select their current city and airplane type from a menu.
- 2.2 Airplane Range Calculation and Destination Suggestion
  - The application shall calculate the maximum non-stop travel distance of the chosen airplane based on its fuel capacity, consumption rates, and range.
  - Using the calculated distance, the application will suggest a list of potential city destinations that can be reached from the user's current location without refueling.
- 2.3. File Loading
  - The application must load airplane model and city data from input files at the start of the program.
  - It should handle file I/O exceptions gracefully and provide appropriate feedback to the user without crashing the system.
- 2.4. Data Validation
  - The application must validate input data for airplane models and cities to ensure they are valid and consistent.
  - It should handle cases of invalid or inconsistent data, such as negative fuel capacity, consumption rates, or invalid geolocation data.
  - If any such issues are encountered, the system should inform the user about the specific error without crashing.
- 2.5. User Input Validation
  - The application must validate user inputs to ensure they are within the expected range and conform to the required data format.
  - If the user enters a choice that is not listed in the menu or provides invalid data, the system should inform the user about the error and allow them to correct their input.
- 2.6. Weather Condition Consideration
  - The application shall prompt the user to enter the current season (e.g., spring, summer, fall, winter).
  - The application shall consider the user’s current season as a factor, some certain weather conditions can impact the distance a plane can reach.
  - The specific impact of each season on the distance calculations shall be determined based on predefined weather indices.
- 2.7 Flight Duration Estimation.
  - The application shall incorporate a computation module capable of calculating the estimated flight duration. This module will use the distance to each potential destination and the cruising speed of the selected aircraft as inputs.
  - A calculation module will estimate flight durations using distance and aircraft speed. The formula used will be time = distance/speed.
  - The estimated durations, in hours and minutes, will be displayed next to each potential destination on the interface.
- 2.8 Fuel Consumption Estimation.
  - The application shall include a fuel estimation module. This will calculate estimated fuel consumption for each potential destination. The module will consider the aircraft's fuel efficiency, the distance to each destination, and data on seasonal variations in fuel efficiency.
  - Estimated fuel consumption will be displayed for each potential destination.
- 2.9 CO2 Emissions Estimation.
  - The application shall include a CO2 emissions estimation module. This module will calculate the estimated CO2 emissions for each potential destination.
  - The CO2 emissions estimation will consider the aircraft's fuel consumption for the proposed flight and the specific CO2 emissions per liter of aviation fuel.
  - The estimated CO2 emissions will be displayed for each potential destination.
- 2.10 Cost of Flight Estimation
  - The application shall incorporate a cost estimation module. This module will calculate the estimated cost of fuel for each potential destination.
  - The cost estimation will be based on the aircraft's estimated fuel consumption for the proposed flight and the current average cost of aviation fuel.
  - The estimated cost of fuel will be displayed for each potential destination.
- 2.11 Expanding Airplane Type Options
  - The application shall extend the number of airplane types available for selection. The addition of more airplane types will increase the depth and precision of distance, duration, fuel consumption, and cost calculations, thereby enhancing the accuracy of potential destinations suggested to the user.
- 2.12 Database Integration for Data Persistence
  - The application shall transition from file-based data loading to database-driven data management.
  - The application must connect to the database, read from it, and handle any database connection issues or exceptions without crashing.
  - The application should include error-handling mechanisms to manage potential issues such as missing data, connection loss, or data format discrepancies.

---

## To-Do List

---

---

## Author

Ding-Nan, Hsu. - 2023/06/10 -
dingnan@bu.edu
